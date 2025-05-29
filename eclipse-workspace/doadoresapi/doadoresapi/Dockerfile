# ... mantém o build ...

# Runtime stage
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/doadoresapi-*.jar app.jar

# Define as variáveis OBRIGATÓRIAS
ENV PORT=8080
ENV SPRING_DATASOURCE_URL=""
ENV SPRING_DATASOURCE_USERNAME=""
ENV SPRING_DATASOURCE_PASSWORD=""

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=${PORT} -Dserver.address=0.0.0.0 -Dspring.datasource.url=\"${SPRING_DATASOURCE_URL}\" -Dspring.datasource.username=\"${SPRING_DATASOURCE_USERNAME}\" -Dspring.datasource.password=\"${SPRING_DATASOURCE_PASSWORD}\" -jar app.jar"]