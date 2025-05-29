# Usar imagem oficial do Java 8
FROM openjdk:8-jdk-alpine

# Definir variável de ambiente para a porta
ENV PORT 8081

# Criar diretório da aplicação
WORKDIR /app

# Copiar o arquivo JAR para dentro do container
COPY target/doadoresapi.jar /app/doadoresapi.jar

# Expor a porta 8081
EXPOSE 8081

# Comando para rodar a aplicação
CMD ["java", "-jar", "doadoresapi.jar"]
