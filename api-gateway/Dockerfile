FROM openjdk:17-jdk
WORKDIR /app

# Copy project files
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

# Ensure mvnw is executable
RUN chmod +x mvnw

CMD ["./mvnw", "spring-boot:run"]