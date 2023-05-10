FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/hebergeur-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} hebergeur.jar
ENTRYPOINT java -jar /hebergeur.jar $SERVER_IP