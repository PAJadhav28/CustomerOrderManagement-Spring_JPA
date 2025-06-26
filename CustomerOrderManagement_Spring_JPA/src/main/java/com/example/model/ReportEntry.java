package com.example.model;

import java.sql.Timestamp;

public class ReportEntry {
    private int reportId;
    private int customerId;
    private String fullName;
    private double totalSpent;
    private Timestamp reportDate;

    public ReportEntry(int reportId, int customerId, String fullName, double totalSpent, Timestamp reportDate) {
        this.reportId = reportId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.totalSpent = totalSpent;
        this.reportDate = reportDate;
    }

    // Getters
    public int getReportId() { return reportId; }
    public int getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public double getTotalSpent() { return totalSpent; }
    public Timestamp getReportDate() { return reportDate; }
}