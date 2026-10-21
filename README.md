# mycoolapp

Application Spring Boot du **TP01** (Spring Boot : Actuator, Spring Security, HealthIndicator custom et profils).

## Fonctionnalités

- `FunRestController` : endpoints `/`, `/teaminfo`, `/workout`
- **Spring Boot Actuator** : `/actuator/health`, `/actuator/info`, `/actuator/beans`, ...
- **Spring Security** (HTTP Basic) : identifiants `abdou` / `cHk3694`
- **HealthIndicator custom** (`FileHealthIndicator`) : vérifie la présence de `data/team.txt`
- **Profils** : `dev` (port 8081) et `prod` (port 8082) ; par défaut port **8080**
- Propriétés custom injectées avec `@Value` : `coach.name`, `team.name`

## Prérequis

- Java 25
- Aucune installation de Maven nécessaire (le wrapper `./mvnw` est fourni)

## Lancer l'application

> ⚠️ Lance l'application **depuis la racine du projet** : le chemin `data/team.txt`
> (surveillé par le HealthIndicator) est relatif au dossier de lancement.

```bash
./mvnw clean package
java -jar target/mycoolapp-0.0.1-SNAPSHOT.jar
```

L'application démarre sur `http://localhost:8080`.

Pour changer de port :

```bash
java -jar target/mycoolapp-0.0.1-SNAPSHOT.jar --server.port=8585
```

## Endpoints et authentification

Lisibles **sans** identifiants :

| URL | Réponse |
|---|---|
| `GET /actuator/health` | `{"status":"UP"}` |

Nécessitent les identifiants `abdou` / `cHk3694` :

| URL | Réponse |
|---|---|
| `GET /` | `Hello World!` |
| `GET /teaminfo` | `Coach: Mickey Mouse, Team name: The Mouse Club` |
| `GET /actuator/info` | infos `info.app.*` |
| `GET /actuator/beans` | liste des beans |

Exemple en ligne de commande :

```bash
curl -u abdou:cHk3694 http://localhost:8080/actuator/health
```

## Structure

```
mycoolapp/
├── data/team.txt                 # fichier surveillé par FileHealthIndicator
├── src/main/java/com/tp1/mycoolapp/
│   ├── MycoolappApplication.java
│   ├── FunRestController.java
│   └── health/FileHealthIndicator.java
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties
│   └── application-prod.properties
├── pom.xml
├── REPONSES.md
└── COMPREHENSION.md
```
