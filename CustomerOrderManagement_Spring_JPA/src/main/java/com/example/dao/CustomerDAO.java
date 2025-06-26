package com.example.dao;

import com.example.model.Customer;
import com.example.model.USACustomerOrder;
import com.example.util.EntityManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAO {

    private final EntityManagerUtil emUtil;

    @Autowired
    public CustomerDAO(EntityManagerUtil emUtil) {
        this.emUtil = emUtil;
    }

    public List<Customer> findAll() {
        EntityManager em = emUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Customer findById(int id) {
        EntityManager em = emUtil.getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Customer customer) {
        EntityManager em = emUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (customer.getCustomerId() == 0) {
                em.persist(customer);
            } else {
                em.merge(customer);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = emUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<USACustomerOrder> getUSACustomerOrders() {
        EntityManager em = emUtil.getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM usa_customer_orders");
            List<Object[]> results = query.getResultList();
            List<USACustomerOrder> usaCustomerOrders = new ArrayList<>();
            for (Object[] row : results) {
                usaCustomerOrders.add(new USACustomerOrder(
                    (Integer) row[0],
                    (String) row[1],
                    (String) row[2],
                    (String) row[3],
                    ((BigDecimal) row[4]).doubleValue()
                ));
            }
            return usaCustomerOrders;
        } finally {
            em.close();
        }
    }

//    public Object[] getCustomerOrderSummary(int customerId) {
//        EntityManager em = emUtil.getEntityManager();
//        try {
//            Query query = em.createNativeQuery("{CALL GetCustomerOrderSummary(?, ?, ?)}");
//            query.setParameter(1, customerId);
//            query.setParameter(2, null);
//            query.setParameter(3, null);
////            query.setParameter(4, null);
//            Object[] result = (Object[]) query.getSingleResult();
//            return new Object[]{result[1], result[2], result[3]}; // fullName, orderCount, totalSpent
//        } finally {
//            em.close();
//        }
//    }
    
    public Object[] getCustomerOrderSummary(int customerId) {
        EntityManager em = emUtil.getEntityManager();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("GetCustomerOrderSummary");

            // Register parameters first
            query.registerStoredProcedureParameter("p_customer_id", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_order_count", Integer.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("p_total_spent", BigDecimal.class, ParameterMode.OUT);

            // Set input parameter
            query.setParameter("p_customer_id", customerId);

            // Execute stored procedure
            query.execute();

            // Get output parameters
            int orderCount = (int) query.getOutputParameterValue("p_order_count");
            BigDecimal totalSpent = (BigDecimal) query.getOutputParameterValue("p_total_spent");

            // Get customer full name separately (not part of the procedure)
            String fullName = (String) em.createQuery(
                "SELECT CONCAT(c.firstName, ' ', c.lastName) FROM Customer c WHERE c.customerId = :customerId")
                .setParameter("customerId", customerId)
                .getSingleResult();

            return new Object[] { fullName, orderCount, totalSpent };

        } finally {
            em.close();
        }
    }


}