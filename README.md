## Task Manager API

A production-ready REST API for managing tasks with secure authentication, validation, role-based authorization, and scalable architecture.

 ## Overview

Task Manager API is a backend application built using Java and Spring Boot following modern backend development best practices.

The project demonstrates how production-ready APIs are designed using layered architecture, DTOs, validation, JWT authentication, exception handling, database relationships, and clean code principles.

This project was built to simulate the standards used by professional software engineering teams.

### Features

####  Authentication & Security
*   **User Registration**
*   **User Login**
*   **JWT Authentication**
*   **BCrypt Password Encryption**
*   **Role-Based Authorization**
*   **Secure Endpoints**
*   **Spring Security**
*   **Authentication Filter**
*   **Authorization Rules**

####  Task Management
*   **Create Task**
*   **Update Task**
*   **Delete Task**
*   **Get Single Task**
*   **Get All Tasks**
*   **Search Tasks**
*   **Filter Tasks**
*   **Sort Tasks**
*   **Pagination**

####  Task Properties
*   **Title**
*   **Description**
*   **Status**
*   **Priority**
*   **Due Date**
*   **Created At**
*   **Updated At**

####  User Management
*   **Register Users**
*   **Update Profile**
*   **Change Password**
*   **User Roles**
*   **User-specific Tasks**

####  Validation
*   **Bean Validation**
*   **Custom Business Validation**
*   **Duplicate Email Prevention**
*   **Invalid Status Prevention**
*   **Invalid Priority Prevention**

####  Exception Handling
*   **Global Exception Handler**
*   **Custom Exceptions**
*   **Validation Error Responses**
*   **Consistent API Response Format**

####  Database & Architecture
*   **MySQL**
*   **Spring Data JPA**
*   **Hibernate**
*   **Entity Relationships**
*   **DTO Pattern**
*   **Service Layer**
*   **Repository Pattern**
*   **Clean Architecture**

####  Production Features
*   **API Versioning** *(optional)*
*   **Logging**
*   **Environment Variables**
*   **Configuration Profiles**



## Architecture
Controller
      │
      ▼
Service Layer
      │
      ▼
Repository
      │
      ▼
MySQL Database


## Tech Stack
- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Jakarta Validation
- Docker
- Git
- GitHub

## Project Structure
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── mapper
 ├── security
 ├── exception
 ├── config
 ├── validation
 └── util

 
## Security Flow
Register
      │
      ▼
Password Encrypted
      │
      ▼
Saved to Database

────────────

Login
      │
      ▼
Authenticate
      │
      ▼
Generate JWT
      │
      ▼
Client Stores Token
      │
      ▼
Token Sent With Requests
      │
      ▼
Access Protected Endpoints


## API Endpoints

- ## Method	   ## Endpoint	          ##  Description
- POST	  /api/auth/register	 Register User
- POST	  /api/auth/login	     Login
- GET	    /api/tasks	         Get Tasks
- GET	    /api/tasks/{id}	     Get Task
- POST	  /api/tasks	         Create Task
- PUT	    /api/tasks/{id}    	 Update Task
- DELETE	/api/tasks/{id}	     Delete Task


## Testing
- Unit Tests
- Integration Tests
- API Testing using Postman
- j-unit test 
- Mockto 


## Getting Started
git clone https://github.com/yourusername/task-manager-api.git

cd task-manager-api

mvn spring-boot:run


## Future Improvements
- Email Verification
- Password Reset
- Refresh Tokens
- Redis Cache
- Notifications
- File Attachments
- WebSocket Notifications
- Docker Compose
- CI/CD Pipeline
- Kubernetes Deployment

## Why This Project?

This project demonstrates professional backend development practices including:

- RESTful API design
- Authentication and authorization
- Clean architecture
- Data validation
- Exception handling
- Database modeling
- Secure coding practices
- Scalable application structure
- Production-ready development workflow
