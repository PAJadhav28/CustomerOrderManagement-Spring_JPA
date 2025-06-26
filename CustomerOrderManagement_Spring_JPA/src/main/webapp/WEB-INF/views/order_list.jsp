<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>Orders</h2>
    <a href="${pageContext.request.contextPath}/orders/add">Add New Order</a>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>Customer ID</th>
            <th>Order Date</th>
            <th>Total Amount</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.customerId}</td>
                <td>${order.orderDate}</td>
                <td>${order.totalAmount}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/orders/edit/${order.orderId}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/orders/delete?id=${order.orderId}" 
                       onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h3>Order Audit Log (Trigger)</h3>
    <table border="1">
        <tr>
            <th>Audit ID</th>
            <th>Order ID</th>
            <th>Customer ID</th>
            <th>Total Amount</th>
            <th>Action</th>
            <th>Action Date</th>
        </tr>
        <c:forEach var="audit" items="${auditLog}">
            <tr>
                <td>${audit.auditId}</td>
                <td>${audit.orderId}</td>
                <td>${audit.customerId}</td>
                <td>${audit.totalAmount}</td>
                <td>${audit.action}</td>
                <td>${audit.actionDate}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>