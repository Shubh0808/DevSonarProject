# ===== Build Stage =====
# Use Maven + JDK 17 image to build the project
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven pom.xml first (to leverage caching)
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the Spring Boot app, skip tests to speed up
RUN mvn clean package -DskipTests

# ===== Run Stage =====
# Use lightweight JRE image to run the app
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 9090 (adjust if needed)
EXPOSE 9090

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=9090"]
