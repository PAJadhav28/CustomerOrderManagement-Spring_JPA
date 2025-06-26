package com.example.dao;

import com.example.model.Order;
import com.example.model.AuditEntry;
import com.example.util.EntityManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAO {

    private final EntityManagerUtil emUtil;

    @Autowired
    public OrderDAO(EntityManagerUtil emUtil) {
        this.emUtil = emUtil;
    }

    public List<Order> findAll() {
        EntityManager em = emUtil.getEntityManager();
        try {
            Query query = em.createNativeQuery(
                "SELECT o.*, c.first_name, c.last_name FROM orders o JOIN customers c ON o.customer_id = c.customer_id");
            List<Object[]> results = query.getResultList();
            List<Order> orders = new ArrayList<>();
            for (Object[] row : results) {
                orders.add(new Order(
                    (Integer) row[0],
                    (Integer) row[1],
                    (java.sql.Date) row[2],
                    ((BigDecimal) row[3]).doubleValue(),
                    (String) row[4],
                    (String) row[5]
                ));
            }
            return orders;
        } finally {
            em.close();
        }
    }

    public Order findById(int id) {
        EntityManager em = emUtil.getEntityManager();
        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Order order) {
        EntityManager em = emUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (order.getOrderId() == 0) {
                em.persist(order);
            } else {
                em.merge(order);
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
            Order order = em.find(Order.class, id);
            if (order != null) {
                em.remove(order);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<AuditEntry> getAuditLog() {
        EntityManager em = emUtil.getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM order_audit");
            List<Object[]> results = query.getResultList();
            List<AuditEntry> auditLog = new ArrayList<>();
            for (Object[] row : results) {
                auditLog.add(new AuditEntry(
                    (Integer) row[0],
                    (Integer) row[1],
                    (Integer) row[2],
                    ((BigDecimal) row[3]).doubleValue(),
                    (String) row[4],
                    (Timestamp) row[5]
                ));
            }
            return auditLog;
        } finally {
            em.close();
        }
    }
}