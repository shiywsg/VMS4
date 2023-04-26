FROM openjdk:18.0-slim
COPY target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app.jar"]