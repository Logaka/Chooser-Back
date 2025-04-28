FROM eclipse-temurin:21-jdk as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven

RUN mvn clean package -DskipTests

#основная сборка
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# Копируем предварительно собранный JAR-файл
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
