# Use imagem com Java 24 (ajuste conforme disponibilidade oficial)
FROM eclipse-temurin:24-jdk

# Crie um diretório para o app
WORKDIR /app

# Copie o arquivo JAR do seu projeto
COPY /target/*.jar app.jar


# Exponha a porta (ajuste conforme necessário)
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
