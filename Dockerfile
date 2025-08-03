# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven build output (you must build the jar first)
COPY target/currency-converter-1.0.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
