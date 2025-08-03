# Stage 1: Build with Maven using Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set workdir
WORKDIR /app

# Copy all project files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Stage 2: Run the built app with JDK 17
FROM eclipse-temurin:17-jdk

# Set workdir
WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/currency-converter-1.0.jar app.jar

# Expose port
EXPOSE 8080

# Start app
ENTRYPOINT ["java", "-jar", "app.jar"]
