FROM openjdk:17-alpine
WORKDIR /app
COPY target/currency-converter-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
