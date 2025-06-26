package com.example.model;

import java.sql.Timestamp;

public class AuditEntry {
    private int auditId;
    private int orderId;
    private int customerId;
    private double totalAmount;
    private String action;
    private Timestamp actionDate;

    public AuditEntry(int auditId, int orderId, int customerId, double totalAmount, String action, Timestamp actionDate) {
        this.auditId = auditId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.action = action;
        this.actionDate = actionDate;
    }

    // Getters
    public int getAuditId() { return auditId; }
    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public double getTotalAmount() { return totalAmount; }
    public String getAction() { return action; }
    public Timestamp getActionDate() { return actionDate; }
}