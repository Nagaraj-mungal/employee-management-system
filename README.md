# Employee Management System (PeopleDesk)

A Spring Boot + Thymeleaf + MySQL employee management application.

## Features
- Admin login
- Dashboard with employee and department counts
- Employee create, view, update and delete
- Search employees by name, email or job title
- Department create, list and delete
- Employee status (Active/Inactive)
- Form validation and sample seed data

## Requirements
- Java 21
- Maven 3.9+
- MySQL 8+

## 1. Create the database
The application can create the database automatically when the MySQL user has permission. You can also run:
```sql
CREATE DATABASE employee_management;
```

## 2. Configure database credentials
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.username=root
spring.datasource.password=root
```
Or set environment variables in Git Bash:
```bash
export DB_USERNAME='root'
export DB_PASSWORD='your_mysql_password'
```
Do not commit real database passwords to Git.

## 3. Run the application
From the project root in Git Bash:
```bash
mvn clean spring-boot:run
```
Open http://localhost:8080

## Login
- Username: `admin`
- Password: `Admin@123`

Change the demo credentials and use a persistent user store before deploying this application publicly.

## Build an executable JAR
```bash
mvn clean package
java -jar target/employee-management-system-1.0.0.jar
```

## Important notes
- Hibernate `ddl-auto=update` creates/updates the tables automatically for this learning project.
- Sample departments and two sample employees are inserted on first startup.
- The demo admin account is in-memory: it resets to the configured demo account whenever the app starts.
- This project is intended as a learning/demo baseline. Before production use, add persistent user management, role-based permissions, audit logs, database migrations, backups, and stronger deployment secrets management.
