# Use Java 17 base image
FROM eclipse-temurin:17-jdk

# Create working directory
WORKDIR /app

# Copy the JAR file from Maven build
COPY target/currency-converter-1.0.jar app.jar

# Expose the Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
