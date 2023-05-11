FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/partie-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} partie.jar
ENTRYPOINT java  -jar /partie.jar $SERVER_IP