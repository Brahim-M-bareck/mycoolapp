# mycoolapp

Application Spring Boot du TP01.

Le projet utilise Spring Boot Actuator, Spring Security, un HealthIndicator
personnalise et des profils.

## Fonctionnalites

- FunRestController : endpoints `/`, `/teaminfo`, `/workout`
- Spring Boot Actuator : `/actuator/health`, `/actuator/info`, `/actuator/beans`
- Spring Security (HTTP Basic) : identifiants `abdou` / `cHk3694`
- HealthIndicator personnalise (FileHealthIndicator) : verifie la presence de `data/team.txt`
- Profils : `dev` (port 8081) et `prod` (port 8082). Par defaut, port 8080
- Proprietes personnalisees injectees avec @Value : `coach.name`, `team.name`

## Prerequis

- Java 25
- Maven n'est pas necessaire, le wrapper `./mvnw` est fourni

## Lancer l'application

Il faut lancer l'application depuis la racine du projet. Le chemin `data/team.txt`
surveille par le HealthIndicator est relatif au dossier de lancement.

```
./mvnw clean package
java -jar target/mycoolapp-0.0.1-SNAPSHOT.jar
```

L'application demarre sur `http://localhost:8080`.

Pour changer de port :

```
java -jar target/mycoolapp-0.0.1-SNAPSHOT.jar --server.port=8585
```

## Endpoints et authentification

Accessible sans identifiants :

- GET /actuator/health : `{"status":"UP"}`

Accessibles avec les identifiants `abdou` / `cHk3694` :

- GET / : `Hello World!`
- GET /teaminfo : `Coach: Mickey Mouse, Team name: The Mouse Club`
- GET /actuator/info : les informations `info.app.*`
- GET /actuator/beans : la liste des beans

Exemple avec curl :

```
curl -u abdou:cHk3694 http://localhost:8080/actuator/health
```

## Structure du projet

```
mycoolapp/
  data/team.txt
  src/main/java/com/tp1/mycoolapp/
    MycoolappApplication.java
    FunRestController.java
    health/FileHealthIndicator.java
  src/main/resources/
    application.properties
    application-dev.properties
    application-prod.properties
  pom.xml
  REPONSES.md
  COMPREHENSION.md
```
