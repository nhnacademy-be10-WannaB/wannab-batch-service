FROM eclipse-temurin:21
ARG JAR_FILE=./target/wannab-batch-service.jar
COPY ${JAR_FILE} wannab-batch-service.jar

ENTRYPOINT ["java","-jar", "/wannab-batch-service.jar"]