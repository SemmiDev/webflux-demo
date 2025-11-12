# Stage 1: Build the application using Maven
FROM maven:3.9.9-amazoncorretto-21 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests  # Skip tests for faster build; run tests separately in CI

# Stage 2: Runtime image (minimal JRE)
FROM eclipse-temurin:21-jre-alpine

# Set non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Set working directory
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (default Spring Boot)
EXPOSE 8080

# Environment variables (override in docker run/compose)
ENV SPRING_PROFILES_ACTIVE=prod \
    SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/productdb \
    JAVA_OPTS="-Xmx512m -Xms256m"

# Healthcheck: Check if app responding (adjust endpoint jika beda)
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the app with JVM options
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
