# Etapa de build
FROM maven:3.8.5-openjdk-8 AS build
WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Faz o build usando o Maven
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM openjdk:8-jre-alpine
WORKDIR /app

# Copia o JAR gerado na etapa build
COPY --from=build /app/target/doadoresapi-*.jar app.jar

# Define variáveis obrigatórias
ENV PORT=8080
ENV SPRING_DATASOURCE_URL=""
ENV SPRING_DATASOURCE_USERNAME=""
ENV SPRING_DATASOURCE_PASSWORD=""

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
