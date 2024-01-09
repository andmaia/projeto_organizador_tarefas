# Use uma imagem base do OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR do aplicativo para o contêiner
COPY ./target/task_self-0.0.1-SNAPSHOT.jar /app/app.jar

# Comando para executar o aplicativo quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
