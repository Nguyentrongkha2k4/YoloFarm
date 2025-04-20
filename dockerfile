# Use an official OpenJDK runtime as a base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the packaged JAR file
COPY target/yolofarm-0.0.1-SNAPSHOT.jar app.jar
# COPY .env /app/.env
# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
