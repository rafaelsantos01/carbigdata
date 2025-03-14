FROM openjdk:21-slim

WORKDIR /app

# Copia o arquivo JAR correto
COPY target/teste-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]