<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Customers</h2>
    <a href="${pageContext.request.contextPath}/customers/add">Add New Customer</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>${customer.country}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/customers/edit?id=${customer.customerId}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/customers/delete?id=${customer.customerId}" onclick="return confirm('Are you sure?')">Delete</a> |
                    <a href="${pageContext.request.contextPath}/customers/summary?id=${customer.customerId}">Summary</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h3>USA Customer Orders (View)</h3>
    <p>Debug: usaCustomerOrders size = ${fn:length(usaCustomerOrders)}</p>
    <table border="1">
        <tr>
            <th>Customer ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Country</th>
            <th>Total Spent</th>
        </tr>
        <c:forEach var="usaCustomer" items="${usaCustomerOrders}">
            <tr>
                <td>${usaCustomer.customerId}</td>
                <td>${usaCustomer.firstName}</td>
                <td>${usaCustomer.lastName}</td>
                <td>${usaCustomer.country}</td>
                <td>${usaCustomer.totalSpent}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty fullName}">
        <h3>Customer Summary (Stored Procedure)</h3>
        <p>Name: ${fullName}</p>
        <p>Order Count: ${orderCount}</p>
        <p>Total Spent: ${totalSpent}</p>
    </c:if>
    <a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>