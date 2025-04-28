FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем предварительно собранный JAR-файл
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]