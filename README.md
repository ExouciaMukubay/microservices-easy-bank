# Easy Bank - Microservices Banking Application

This is a simple banking application named **Easy Bank**, built by following the Udemy course  
**"Master Microservices with Spring Boot, Docker, and Kubernetes"**.

The project follows a microservices architecture and is designed to demonstrate the fundamentals of modern backend development using Spring Boot.

## 💡 Overview

The application consists of the following core microservices:

- **Account Service**: Manages customer account information  
- **Cards Service**: Handles customer credit/debit card details  
- **Loans Service**: Provides loan-related information and operations

Each microservice is developed using **Spring Boot**, with separate data models, endpoints, and database connections. They are structured for scalability and independence.

## 🧱 Technologies Used

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Cloud  
- REST APIs  
- Maven

## 🚧 Work in Progress

- **Docker integration** for containerizing all services will be added later in the course  
- **Kubernetes deployment** for orchestration and scaling will follow in the advanced sections

## 🔧 How to Run

Each microservice can be started independently using:

```bash
./mvnw spring-boot:run
