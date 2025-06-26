<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Order Management</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Welcome to Customer Order Management</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/customers">Manage Customers</a></li>
        <li><a href="${pageContext.request.contextPath}/orders">Manage Orders</a></li>
        <li><a href="${pageContext.request.contextPath}/report">View USA Spending Report</a></li>
        <li><a href="${pageContext.request.contextPath}/debug">Debug Session</a></li>
    </ul>
</body>
</html>