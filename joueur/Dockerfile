FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/joueur-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} joueur.jar
ENTRYPOINT java -jar /joueur.jar $NOM $SERVER_IP $LANCEMENT