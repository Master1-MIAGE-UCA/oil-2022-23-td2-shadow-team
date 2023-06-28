README: Comment lancer l'application

Pour exécuter dans Docker, suivez les étapes ci-dessous:

1. Générez les images en exécutant les commandes suivantes :
    ```
    mvn clean install
    docker build -t webclient:partie partie
    docker build -t webclient:joueur joueur
    docker build -t webclient:appariement appariement
    ```

2. Lancez l'application avec Docker Compose :
    ```
    docker-compose up
    ```

Pour lancer manuellement en local, suivez les étapes ci-dessous:

1. Générez les fichiers nécessaires en exécutant la commande suivante :
    ```
    mvn clean install
    ```

2. Lancez les différents composants de l'application avec mvn utilisez les commandes suivantes :
    ```
    mvn exec:java -pl appariement
    mvn exec:java -pl partie -Dserver.port=8090 -Dexec.args="http://localhost:8080/"
    mvn exec:java -pl joueur -Dserver.port="8081" -Dexec.args="Michel http://localhost:8080/"
    mvn exec:java -pl joueur -Dserver.port="8082" -Dexec.args="Nathalie http://localhost:8080/true"
    ```

Ces commandes lanceront l'application en mode local avec les ports configurés et les noms d'utilisateurs Michel et Nathalie.
