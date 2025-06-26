<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Session Debug</title>
</head>
<body>
    <h2>Session Attributes</h2>
    <ul>
        <% 
            Enumeration<String> attributes = session.getAttributeNames();
            while (attributes.hasMoreElements()) {
                String attr = attributes.nextElement();
                out.println("<li>" + attr + ": " + session.getAttribute(attr) + "</li>");
            }
        %>
    </ul>
    <a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>