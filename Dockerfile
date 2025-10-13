# Use an official Java runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from your target folder
COPY target/E-com-Product-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that Spring Boot will run on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
