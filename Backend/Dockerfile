FROM eclipse-temurin:17-jdk-jammy as builder

LABEL authors="Jackson McGillivary"

# Set working directory
WORKDIR /app

# Copy maven files first for better caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make the mvnw script executable
RUN chmod +x mvnw

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Create final image with just the JAR
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/loan-origination-system-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (typically 8080 for Spring Boot)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
