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

## Captures d'ecran

Les preuves demandees sont disponibles dans le dossier `image/` :

| Element demande | Capture |
| --- | --- |
| Reponse de `/` | [`image/Capture3.png`](image/Capture3.png) |
| Reponse de `/teaminfo` | [`image/Capture4.png`](image/Capture4.png) |
| Reponse de `/actuator/health` | [`image/Capture5.png`](image/Capture5.png) |
| Reponse de `/actuator/info` | [`image/Capture6.png`](image/Capture6.png) |
| `/actuator/beans` sans identifiants : 401 | [`image/Capture7.png`](image/Capture7.png) |
| `/actuator/beans` avec identifiants : 200 | [`image/Capture8.png`](image/Capture8.png) |
| Console : demarrage sur le port 8585 | [`image/Capture9.1.png`](image/Capture9.1.png) |
| `/actuator/health` sur le port 8585 | [`image/Capture9.2.png`](image/Capture9.2.png) |

## Structure du projet

```
mycoolapp/
  data/team.txt
  image/                      captures d'ecran du rendu
  src/main/java/com/tp1/mycoolapp/
    MycoolappApplication.java
    FunRestController.java
    health/FileHealthIndicator.java
  src/main/resources/
    application.properties
    application-dev.properties
    application-prod.properties
  src/test/java/com/tp1/mycoolapp/
    MycoolappApplicationTests.java
  pom.xml
  REPONSES.md
  COMPREHENSION.md
```
