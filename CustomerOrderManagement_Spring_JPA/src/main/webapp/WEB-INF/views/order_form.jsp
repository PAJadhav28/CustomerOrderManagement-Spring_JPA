<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>${order.orderId == null ? 'Add Order' : 'Edit Order'}</h2>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/orders" method="post">
        
        <%-- <input type="hidden" name="orderId" value="${order.orderId}">
        <label>Order ID:</label>
        <input type="number" name="orderId" value="${order.orderId}" required><br>
         --%>
          <input type="hidden" name="orderId" value="${order.orderId}">
         <c:if test="${order.orderId != null}">
		    <label>Order ID:</label>
		    <input type="number" name="orderId" value="${order.orderId}" readonly><br>
		</c:if>
         
        <label>Customer ID:</label>
        <input type="number" name="customerId" value="${order.customerId}" required><br>
        <label>Order Date (YYYY-MM-DD):</label>
        <input type="date" name="orderDate" value="${order.orderDate}" required><br>
        <label>Total Amount:</label>
        <input type="number" step="0.01" name="totalAmount" value="${order.totalAmount}" required><br>
        <input type="submit" value="Save">
    </form>
    <a href="${pageContext.request.contextPath}/orders">Back to Order List</a>
</body>
</html>