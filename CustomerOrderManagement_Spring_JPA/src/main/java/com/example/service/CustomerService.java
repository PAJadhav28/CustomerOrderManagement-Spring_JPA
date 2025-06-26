package com.example.service;

import com.example.dao.CustomerDAO;
import com.example.model.Customer;
import com.example.model.USACustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerDAO.findById(id);
    }

    public void saveCustomer(Customer customer) {
    	System.out.println("saveCustomer: "+customer);
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First Name is required.");
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last Name is required.");
        }
        if (customer.getEmail() == null || !customer.getEmail().matches("^[\\w-\\.]+@[\\w-]+\\.[\\w-]{2,}$")) {
            throw new IllegalArgumentException("Email must be valid.");
        }
        if (customer.getCountry() == null || customer.getCountry().trim().isEmpty()) {
            throw new IllegalArgumentException("Country is required.");
        }
        customerDAO.save(customer);
    }

    public void deleteCustomer(int id) {
        customerDAO.delete(id);
    }

    public List<USACustomerOrder> getUSACustomerOrders() {
        return customerDAO.getUSACustomerOrders();
    }

    public Object[] getCustomerOrderSummary(int customerId) {
        return customerDAO.getCustomerOrderSummary(customerId);
    }
}