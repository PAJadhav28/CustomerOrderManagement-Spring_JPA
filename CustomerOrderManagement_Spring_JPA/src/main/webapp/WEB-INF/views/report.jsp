<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>USA Customer Spending Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>USA Customer Spending Report (Cursor)</h2>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <table border="1">
        <tr>
            <th>Report ID</th>
            <th>Customer ID</th>
            <th>Full Name</th>
            <th>Total Spent</th>
            <th>Report Date</th>
        </tr>
        <c:forEach var="report" items="${report}">
            <tr>
                <td>${report.reportId}</td>
                <td>${report.customerId}</td>
                <td>${report.fullName}</td>
                <td>${report.totalSpent}</td>
                <td>${report.reportDate}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>