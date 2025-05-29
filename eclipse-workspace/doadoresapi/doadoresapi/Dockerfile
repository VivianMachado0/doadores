# Etapa 1 - Build
FROM maven:3.8.7-openjdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2 - Runtime
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/doadoresapi-0.0.1-SNAPSHOT.jar doadoresapi.jar
EXPOSE 8081
CMD ["java", "-jar", "doadoresapi.jar"]