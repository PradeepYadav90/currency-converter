# Stage 1: Build with Maven using Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run with minimal JDK
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=build /app/target/currency-converter-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
