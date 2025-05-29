# Etapa 1 - Build
FROM maven:3.8.7-openjdk-8 AS build
WORKDIR /app

# Copia só os arquivos de configuração para aproveitar cache do Docker em builds futuros
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2 - Runtime
FROM openjdk:8-jdk-alpine
WORKDIR /app

# Copia o jar gerado na etapa de build
COPY --from=build /app/target/doadoresapi-0.0.1-SNAPSHOT.jar doadoresapi.jar

EXPOSE 8081

CMD ["java", "-jar", "doadoresapi.jar"]
