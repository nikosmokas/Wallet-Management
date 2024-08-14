# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the build stage
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Download dependencies and build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine

# Set the working directory in the runtime stage
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/wallet-management-0.0.1-SNAPSHOT.jar /app/wallet-management.jar

# Expose the port that Spring Boot runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/wallet-management.jar"]
