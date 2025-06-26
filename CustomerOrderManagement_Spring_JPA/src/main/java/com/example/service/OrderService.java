package com.example.service;

import com.example.dao.OrderDAO;
import com.example.model.Order;
import com.example.model.AuditEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    public Order getOrderById(int id) {
        return orderDAO.findById(id);
    }

    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    public void deleteOrder(int id) {
        orderDAO.delete(id);
    }

    public List<AuditEntry> getAuditLog() {
        return orderDAO.getAuditLog();
    }
}