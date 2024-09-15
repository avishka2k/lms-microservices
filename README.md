# LMS Microservices

This repository contains the code for a Learning Management System (LMS) built using Spring Boot microservices. The project consists of several services including Discovery Server, API Gateway, and various microservices for managing students, staff, courses, enrollments, content, and notifications.

<div align="center">
  <a href="https://sonarcloud.io/summary/new_code?id=avishka2k_lms-microservices" target="_blank">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=avishka2k_lms-microservices&metric=alert_status" alt="Quality Gate Status">
  </a>
  <a href="https://sonarcloud.io/summary/new_code?id=avishka2k_lms-microservices" target="_blank">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=avishka2k_lms-microservices&metric=security_rating" alt="Quality Gate Status">
  </a>
  <a href="https://sonarcloud.io/summary/new_code?id=avishka2k_lms-microservices" target="_blank">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=avishka2k_lms-microservices&metric=sqale_rating" alt="Quality Gate Status">
  </a>
</div>

## Technology Stack 
### Backend Technologies
1. Spring Boot: The primary framework used for building the microservices.
2. Java: The programming language used for developing the microservices.
3. Spring Cloud: For building distributed systems and microservices.
    - Spring Cloud Config: For centralized configuration management.
    - Spring Cloud Netflix Eureka: For service discovery.
    - Spring Cloud Gateway: For API Gateway.
    - Spring Cloud OpenFeign: Communication between microservices

5. Lombok: An annotations Java library that is used to reduce boilerplate code.
6. Apache Kafka: Distributed event store and stream-processing
    - Kafka Consumer:  KafkaConsumerConfig.java (Course service)
    - Kafka Producer:  KafkaTopicConfig.java (University service)
7. Spring Security: Protecting endpoints by using two forms of OAuth 2.0 Bearer Tokens

### Frontend Technologies
1. ReactJs: The primary framework used for building the front end.
2. JavaScript with TypeSript: The Programming language used for developing frontend
3. Amplify UI: Used for Integration with AWS Cognito
4. Axios: Http client  (api.ts)
5. Bootstrap: The programming language used for developing the microservices.

### Database Technologies
1. PostgreSql: The primary application database.
2. Pageadmin4: Interact with the database

### Secret Management
1. Vault by HashiCorp: Manage backend secrets and protect sensitive data.
2. Dotenv: Load environment variables.

### Authentication and Authorization
1. Amazon Cognito User Pool: User authentication and access control.
2. JWT: Used for secure authentication and authorization.
3. RBAC: Manage user permissions (Admin / Student/ Lecturer)
4. Amplify Auth: Authentication and authorization for frontend using AWS Cognito

### Containerization and Orchestration
1. Docker: For containerizing the microservices.
2. Docker Compose: For orchestrating multi-container Docker applications.

### DevOps Tools
1. Github Action: Build and analyze (with sonarcloud)
2. Terraform: Automated creating AWS Cognito user group with admin use

### Others
1. AWS Lambda: Cognito pre token generation trigger.
2. Amazon S3: Store static content
