# Stage 1: Build the application using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy the pom.xml and download dependencies without building the project
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the application
COPY src /app/src
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image using Amazon Corretto
FROM amazoncorretto:17-alpine

WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/diagram-0.0.1-SNAPSHOT.jar /app/diagram-0.0.1-SNAPSHOT.jar

# Expose the port that the application will run on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "/app/diagram-0.0.1-SNAPSHOT.jar"]
