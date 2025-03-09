# Build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy everything to the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17.0.1-jdk-slim

# Set working directory in the runtime container
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/seeker-0.0.1.jar app.jar

# Expose the application's port
EXPOSE 8080

# Set the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
