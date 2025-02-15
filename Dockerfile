# Node.js stage: Build the Angular frontend
FROM node:18.19.1 as node
COPY frontend/ /frontend
WORKDIR /frontend
RUN npm install
RUN npm run build --prod

# Maven build stage: Build the Spring Boot backend
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml for caching dependencies
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy source code and build the app
COPY src/ ./src/
COPY --from=node /frontend/dist/product-service/browser /app/src/main/resources/public
RUN mvn -B clean package -DskipTests=true


# Runtime stage: Create the final Docker image
FROM openjdk:17-jdk-slim-buster AS runtime
WORKDIR /app

# Copy the built Spring Boot app
COPY --from=build /app/target/*.jar app.jar

# Expose port and run the app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
