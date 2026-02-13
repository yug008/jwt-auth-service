# jwt-auth-service
A Spring Boot authentication service implementing JWT-based authentication using Spring Security with an in-memory user store.
This project demonstrates custom security configuration, stateless session management, and token-based authentication.

🚀 Features

•✅ Spring Boot 3

•✅ Spring Security 6

•✅ JWT Generation & Validation

•✅ Stateless Session Management

•✅ Custom SecurityFilterChain

•✅ BCrypt Password Encoding

•✅ REST API-based authentication

🛠 Tech Stack

•Java 17+

•Spring Boot

•Spring Security

•JWT (JSON Web Token)

•Maven

src/main/java/
 ├── config/        → Security configuration (SecurityFilterChain, PasswordEncoder, etc.)
 ├── controller/    → Authentication REST endpoints
 ├── service/       → Business logic & JWT handling
 ├── filter/        → JWT request filter for token validation
 ├── model/         → Entity classes (User model)
 └── repository/    → Data access layer (UserRepository interface)

src/main/resources/
 └── application.yaml  → Application configuration

src/main/java/
 ├── config/        → Security configuration (SecurityFilterChain, PasswordEncoder, etc.)
 ├── controller/    → Authentication REST endpoints
 ├── service/       → Business logic & JWT handling
 ├── filter/        → JWT request filter for token validation
 ├── model/         → Entity classes (User model)
 └── repository/    → Data access layer (UserRepository interface)

src/main/resources/
 └── application.yaml  → Application configuration
 
📌 Current Version

•Uses In-Memory User Store

•No database integration (DB-backed version coming soon)

🔄 Future Improvements

 •Convert to DB-backed authentication (JPA + MySQL)

 •Role-based authorization

 •Refresh token support

 •Dockerization

👨‍💻 Author

Yug Mehta
