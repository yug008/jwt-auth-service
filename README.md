# JWT Authentication Service 

A Spring Boot REST API implementing JWT-based authentication with database-backed user management using Spring Security. Originally implemented with in-memory authentication, later migrated to database-backed JWT authentication.


## 🚀 Features

- User authentication with JWT
- Database-backed authentication (MySQL)
- Custom UserDetailsService
- Password encryption using BCrypt
- Stateless session management
- Role-based authorization
- Secure REST APIs

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- Maven

---

## 🏗 Architecture Flow

1. Client sends login request
2. AuthenticationManager authenticates credentials
3. CustomUserDetailsService fetches user from database
4. JWT token is generated
5. Client sends JWT in Authorization header for protected endpoints

---

## 📁 Project Structure

src/main/java/com/yugmehta/auth_service

├── Configuration
│   └── SecurityConfig.java
│
├── controller
│   ├── AuthController.java
│   ├── HelloController.java
│   └── StudentController.java
│
├── filter
│   └── JwtFilter.java
│
├── model
│   ├── LoginRequest.java
│   ├── Student.java
│   └── User.java
│
├── repository
│   └── UserRepository.java
│
├── service
│   ├── JwtService.java
│   └── UserService.java
│
└── AuthServiceApplication.java

## 🔐 API Endpoints

### Login
POST /login

Request Body:
{
  "username": "user",
  "password": "password"
}

Response:
{
  "token": "JWT_TOKEN"
}

### Protected Endpoint Example
GET /hello

Header:
Authorization: Bearer <JWT_TOKEN>


## ▶️ Run the Application

mvn spring-boot:run  
OR run directly from your IDE.


📌 Future Improvements:

•Add user registration endpoint

•Implement refresh tokens

•Add role-based authorization (ADMIN/USER)

•Add Docker support

•Add unit & integration tests


👨‍💻 Author

Yug Mehta


