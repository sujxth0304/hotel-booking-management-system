# Hotel Booking Management System (Backend)

A **Hotel Booking Management System backend** built using **Java Spring Boot**, **Hibernate (JPA)**, **MySQL**, and **JWT-based authentication**.  
This project provides a secure and scalable REST API for managing hotel rooms, users, and bookings.

The frontend is planned to be developed using **Angular**.

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- MySQL
- Maven

### Frontend (Planned)
- Angular
- TypeScript
- Angular Material / Bootstrap

---

## Features

- JWT-based Authentication & Authorization
- User Registration & Login
- Hotel & Room Management
- Room Booking Management
- Booking History
- Role-based access control
- RESTful APIs
- MySQL database integration

---

## Project Architecture

- Controller Layer – Handles REST API endpoints
- Service Layer – Business logic
- Repository Layer – Database access using JPA
- Security Layer – JWT filters, authentication, authorization
- Entity Layer – Database models

---

## Project Structure (Backend)

```
src/main/java
 └── com.example.hotelbooking
     ├── controller
     ├── service
     ├── repository
     ├── model
     ├── security
     └── config
```

---

## Authentication Flow

1. User registers or logs in
2. Backend generates a JWT token
3. Token is sent in the Authorization header  

```
Authorization: Bearer <JWT_TOKEN>
```

4. Secured endpoints validate the token before granting access

---

## Database Configuration

Update your **application.properties**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret
jwt.expiration=86400000
```

---

## Running the Application

### Prerequisites
- Java 17+
- MySQL
- Maven

### Steps

```bash
git clone https://github.com/sujxth0304/hotel-booking-management-system.git
cd hotel-booking-management-system
mvn clean install
mvn spring-boot:run
```

Backend will start at:

```
http://localhost:8080
```

---

## API Testing

You can test APIs using:
- Postman
- Thunder Client
- Swagger (if enabled)

Make sure to include the JWT token for secured routes.

---

## Frontend (Angular) – Upcoming

Planned features:
- User authentication UI
- Room browsing & booking
- Booking history dashboard
- Admin management panel

---

## Future Enhancements

- Swagger API documentation
- Payment gateway integration
- Email notifications
- Admin analytics dashboard
- Dockerization
- CI/CD pipeline

---

## Author

**Sujith Santhosh**  
GitHub: https://github.com/sujxth0304  

---

## License

This project is licensed under the MIT License.
