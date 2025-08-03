# Use OpenJDK base image
FROM openjdk:17-alpine

# Set working directory
WORKDIR /app

# Copy project files
COPY src/CurrencyConverter.java .

# Compile Java source
RUN javac CurrencyConverter.java

# Run the app
CMD ["java", "CurrencyConverter"]
 