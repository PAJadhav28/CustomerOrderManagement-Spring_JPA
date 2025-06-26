<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Form</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function validateForm() {
            const customerId = document.forms["customerForm"]["customer_id"].value;
            const firstName = document.forms["customerForm"]["first_name"].value;
            const lastName = document.forms["customerForm"]["last_name"].value;
            const email = document.forms["customerForm"]["email"].value;
            const country = document.forms["customerForm"]["country"].value;
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (isNaN(customerId) || customerId <= 0) {
                alert("Customer ID must be a positive number.");
                return false;
            }
            if (!firstName || firstName.trim() === "") {
                alert("First Name is required.");
                return false;
            }
            if (!lastName || lastName.trim() === "") {
                alert("Last Name is required.");
                return false;
            }
            if (!email || !emailRegex.test(email)) {
                alert("Please enter a valid email address.");
                return false;
            }
            if (!country || country.trim() === "") {
                alert("Country is required.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>${customer.customerId == 0 ? 'Add Customer' : 'Edit Customer'}</h2>
   <form name="customerForm" action="${pageContext.request.contextPath}/customers" method="post">
	    <input type="hidden" name="action" value="${customer.customerId == 0 ? 'add' : 'update'}">
	    <label>Customer ID:</label>
	    <input type="number" name="customerId" value="${customer.customerId}" ${customer.customerId != 0 ? 'readonly' : ''} required><br>
	    <label>First Name:</label>
		<input type="text" name="firstName" value="${customer.firstName != null ? customer.firstName : ''}" required><br>
		<label>Last Name:</label>
		<input type="text" name="lastName" value="${customer.lastName != null ? customer.lastName : ''}" required><br>
   		<label>Email:</label>
	    <input type="email" name="email" value="${customer.email != null ? customer.email : ''}" required><br>
	    <label>Country:</label>
	    <input type="text" name="country" value="${customer.country != null ? customer.country : ''}" required><br>
	    <input type="submit" value="Save">
	</form>
    <a href="${pageContext.request.contextPath}/customers">Back to Customer List</a>
</body>
</html>