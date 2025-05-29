#!/bin/sh

# Configura as propriedades do servidor
export JAVA_OPTS="-Dserver.port=$PORT -Dserver.address=0.0.0.0"

# Inicia a aplicação Spring Boot
echo "🟢 Iniciando aplicação Spring Boot..."
exec java $JAVA_OPTS -jar app.jar