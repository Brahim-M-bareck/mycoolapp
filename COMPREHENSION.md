# Fiche des question de comprehension

## Exercice 5.1 bis - Pourquoi Tomcat n'est pas a installer
Tomcat est embarque dans l'application : le starter spring-boot-starter-webmvc ajoute la dependance tomcat-embed-core.
Le plugin Spring Boot la met dans le fat jar et au demarrage Spring Boot lance Tomcat dans le meme processus Java.
Les logs le montrent avec Starting Servlet engine puis Tomcat started on port 8080.