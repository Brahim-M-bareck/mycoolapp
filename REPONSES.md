# REPONSES.md - TP01 Spring Boot 4 (mycoolapp)

## Question 1
@SpringBootConfiguration : la classe peut declarer des beans Spring.
@EnableAutoConfiguration : Spring Boot configure seul les beans selon les dependances du classpath.
@ComponentScan : Spring cherche les beans dans le package de la classe principale et ses sous-packages.
Un controleur dans un package frere n'est pas trouve donc l'endpoint donne 404.

## Question 2
L'auto-configuration cree et configure les beans toute seule selon les dependances du classpath.
Exemple : en ajoutant spring-boot-starter-webmvc Spring Boot demarre Tomcat sur le port 8080 et configure Spring MVC et Jackson pour le JSON sans aucune configuration.

## Question 3
spring-boot:run lance l'application depuis target/classes avec Maven. java -jar lance le fat jar qui contient toutes les dependances.
DevTools est actif seulement avec spring-boot:run car il n'est pas inclus dans le jar.

## Question 4
Spring Security genere un mot de passe aleatoire a chaque demarrage car aucun mot de passe n'est ecrit dans la bibliotheque.
C'est un bon comportement : personne ne connait le mot de passe a l'avance donc pas de faille avec un mot de passe par defaut.
On le personnalise car un mot de passe qui change a chaque redemarrage est inutilisable en production.

## Question 5
Ordre de priorite : argument de ligne de commande puis variable d'environnement puis application.properties puis valeur par defaut.
Avec 8080 dans le fichier et --server.port=9090 le port utilise est 9090.
Interet : un meme JAR pour dev staging et prod sans recompiler.

## Question 6
management.info.env.enabled=true publie les proprietes info.* dans /actuator/info.
Sans cette ligne /actuator/info renvoie {}.

## Question 7
/actuator/beans montre toute l'architecture interne de l'application.
/actuator/env montre les proprietes et parfois des secrets.
Un attaquant peut s'en servir pour preparer une attaque donc il faut une authentification en production.

## Question 8
@RestController est @Controller plus @ResponseBody.
Avec @Controller la valeur retournee est un nom de vue HTML. Avec @RestController elle devient directement la reponse HTTP.

## Bonus 1 - Profils Spring
application-dev.properties donne le port 8081 et application-prod.properties donne le port 8082.
Avec --spring.profiles.active=prod l'application demarre sur 8082.
Un argument de ligne de commande comme --server.port=9090 reste plus fort que le profil.

## Bonus 2 - HealthIndicator custom
FileHealthIndicator verifie la presence d'un fichier.
Fichier present : /actuator/health donne UP.
Fichier absent : il donne DOWN avec le code HTTP 503.

## Bonus 3 - Restreindre Actuator
Avec include=health info metrics seuls 3 endpoints sont exposes.
/actuator/beans donne 404 meme avec les bons identifiants.