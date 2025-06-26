package com.example.model;

public class USACustomerOrder {
    private int customerId;
    private String firstName;
    private String lastName;
    private String country;
    private double totalSpent;

    public USACustomerOrder(int customerId, String firstName, String lastName, String country, double totalSpent) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.totalSpent = totalSpent;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCountry() { return country; }
    public double getTotalSpent() { return totalSpent; }
}