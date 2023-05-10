# Résumé de l'itération actuelle

Dans cette itération, nous avons travaillé sur les aspects technologiques et fonctionnels suivants:

## Aspects technologiques:

Docker-compose: Nous avons créé un fichier docker-compose.yml pour construire et lancer deux conteneurs. Chaque conteneur est responsable d'exécuter un service à l'aide de son fichier JAR correspondant.

Travis CI: Un fichier .travis.yml a été ajouté pour configurer l'intégration continue. Il permet de construire le projet et d'exécuter les tests unitaires automatiquement à chaque push sur le dépôt.

Maven: Le projet utilise Maven comme système de gestion de projets et de dépendances. Nous avons configuré un pom.xml parent et des fichiers pom.xml enfants pour organiser les modules du projet et gérer les dépendances.

Services Spring: Les services Spring ont été développés pour communiquer avec d'autres services. Ils permettent d'échanger des informations et de coordonner les actions entre les différentes parties du système.

Tests unitaires: Nous avons écrit des tests unitaires pour le projet en utilisant les frameworks JUnit et Mockito. Ces tests sont pour l'instant symboliques.


## Aspects fonctionnels:

Pour le moment, nos services ne sont pas encore pleinement fonctionnels et ne remplissent pas encore de rôles précis.

# Objectifs pour l'itération suivante

Dans la prochaine itération, nous prévoyons d'aborder les points suivants:

Architecture REST: Nous allons concevoir nos services Web selon les principes de l'architecture REST.

Communication entre les services: Nous travaillerons sur la mise en place de la communication entre les différents services ainsi que leur roles. Nous définirons les contrats d'interface entre les services pour garantir leur bonne interaction.

Spike sur l'IA du jeu: Nous allons également effectuer un spike pour étudier les différentes options pour créer une IA performante au jeux Yams.


# Installation

This project uses Java 17 and Maven 3.8.0
Installation

Before running this project, please make sure you have the following installed on your machine:

    Java 17
    Maven 3.8.0

If you need to install or update Java or Maven, you can find instructions on their official websites:

    Java SE Downloads
    Apache Maven Downloads

Usage

To build and run the tests for this project, run the following command in the root directory of the project:

```bash

mvn clean test
```

This will compile the source code, run the tests, and produce a report of the results.

