# lms-microservices


Application folder structure

```
lms-microservices/
│
├── discovery-server/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── eureka/
│   │   │   │               └── DiscoveryServerApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── api-gateway/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── gateway/
│   │   │   │               └── ApiGatewayApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── student-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── userservice/
│   │   │   │               ├── controller/
│   │   │   │               ├── model/
│   │   │   │               ├── repository/
│   │   │   │               ├── service/
│   │   │   │               └── StudetServiceApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
|
├── staff-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── userservice/
│   │   │   │               ├── controller/
│   │   │   │               ├── model/
│   │   │   │               ├── repository/
│   │   │   │               ├── service/
│   │   │   │               └── StaffServiceApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── course-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── courseservice/
│   │   │   │               ├── controller/
│   │   │   │               ├── model/
│   │   │   │               ├── repository/
│   │   │   │               ├── service/
│   │   │   │               └── CourseServiceApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── enrollment-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── enrollmentservice/
│   │   │   │               ├── controller/
│   │   │   │               ├── model/
│   │   │   │               ├── repository/
│   │   │   │               ├── service/
│   │   │   │               └── EnrollmentServiceApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── content-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── contentservice/
│   │   │   │               ├── controller/
│   │   │   │               ├── model/
│   │   │   │               ├── repository/
│   │   │   │               ├── service/
│   │   │   │               └── ContentServiceApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── notification-service/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── notificationservice/
│   │   │   │               ├── controller/
│   │   │   │               ├── model/
│   │   │   │               ├── repository/
│   │   │   │               ├── service/
│   │   │   │               └── NotificationServiceApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── config-server/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── configserver/
│   │   │   │               └── ConfigServerApplication.java
│   │   │   ├── resources/
│   │   │       └── application.yml
│   ├── pom.xml
│
├── docker-compose.yml
└── README.md

```
