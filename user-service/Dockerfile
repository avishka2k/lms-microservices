FROM openjdk:17-jdk
WORKDIR /app

# Copy project files
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

# Ensure mvnw is executable
RUN chmod +x mvnw

# Copy the entrypoint script
COPY entrypoint.sh /entrypoint.sh

# Make the entrypoint script executable
RUN chmod +x /entrypoint.sh

# Set the entrypoint script to be executed
ENTRYPOINT ["/entrypoint.sh"]
CMD ["./mvnw", "spring-boot:run"]