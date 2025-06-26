# Customer Order Management System

A simple web-based application to manage customers and their orders. This project demonstrates the use of Spring MVC and Spring ORM (with Hibernate as JPA provider) for building a CRUD-based enterprise Java application.

## Features

- Add, update, delete, and view customers.
- Add, update, delete, and view customer orders.
- One-to-Many relationship: One customer can have multiple orders.
- JSP-based UI with form validation.
- Uses MySQL as the database.
- WAR packaging for deployment on servers like Tomcat.

## Technologies Used

| Layer         | Technology                    |
|---------------|-------------------------------|
| Frontend      | JSP, JSTL, HTML, CSS           |
| Backend       | Spring MVC, Spring ORM         |
| Persistence   | JPA (Hibernate)                |
| Database      | MySQL                          |
| Build Tool    | Maven                          |
| Packaging     | WAR                            |
| IDE           | Eclipse / IntelliJ             |

## Project Structure

```
CustomerOrderManagement-Spring_JPA/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.customerorder/
│   │   │       ├── controller/
│   │   │       ├── dao/
│   │   │       ├── entity/
│   │   │       └── service/
│   │   ├── resources/
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── views/
│   │       └── index.jsp
├── pom.xml
```

## Workflow

1. User submits a form (Add Customer / Add Order).
2. Controller receives the request and maps it to a model.
3. Service layer handles the business logic.
4. DAO layer interacts with the database using Hibernate.
5. Response is returned to the view.

## Database Schema

### Customer Table
- `id` (Primary Key)
- `name`
- `email`

### Order Table
- `id` (Primary Key)
- `orderDate`
- `amount`
- `customer_id` (Foreign Key)

## Setup Instructions

1. **Clone the project**  
   ```
   git clone https://github.com/yourusername/CustomerOrderManagement-Spring_JPA.git
   ```

2. **Import as Maven Project** in Eclipse or IntelliJ.

3. **Configure MySQL database**
   - Create a database: `customer_order_db`
   - Update DB credentials in `application.properties` or `hibernate.cfg.xml`.

4. **Build the project**
   ```
   mvn clean install
   ```

5. **Deploy the WAR** on Tomcat or any servlet container.

6. Access the application at:
   ```
   http://localhost:8080/CustomerOrderManagement-Spring_JPA/
   ```

## Output
<img src="https://github.com/PAJadhav28/CustomerOrderManagement-Spring_JPA/blob/main/output/localhost_8082_CustomerOrderManagement-Spring_JPA_.png">
<img src="https://github.com/PAJadhav28/CustomerOrderManagement-Spring_JPA/blob/main/output/localhost_8082_CustomerOrderManagement-Spring_JPA_customers.png">
<img src="https://github.com/PAJadhav28/CustomerOrderManagement-Spring_JPA/blob/main/output/localhost_8082_CustomerOrderManagement-Spring_JPA_customers_add.png">
<img src="https://github.com/PAJadhav28/CustomerOrderManagement-Spring_JPA/blob/main/output/localhost_8082_CustomerOrderManagement-Spring_JPA_customers_edit_id%3D1.png">
<img src="https://github.com/PAJadhav28/CustomerOrderManagement-Spring_JPA/blob/main/output/localhost_8082_CustomerOrderManagement-Spring_JPA_customers_summary_id%3D2.png">
<img src="https://github.com/PAJadhav28/CustomerOrderManagement-Spring_JPA/blob/main/output/localhost_8082_CustomerOrderManagement-Spring_JPA_orders.png">
<img src="https://github.com/PAJadhav28/CustomerOrderManagement-Spring_JPA/blob/main/output/localhost_8082_CustomerOrderManagement-Spring_JPA_report.png">

## Future Enhancements

- Add pagination and sorting.
- Add role-based login.
- Implement REST APIs.
- Add validation using Hibernate Validator.

## Author

**Pallavi Jadhav**  
B.E. Computer Science, Class of 2023  
[LinkedIn](https://www.linkedin.com/) | [GitHub](https://github.com/)

---

> Built with ❤️ using Java, Spring, Hibernate, and JSP.
