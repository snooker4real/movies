# movies

A movie backend API

**Stack** : Java 17, Maven, Spring Boot 3.0.5, MongoDB, JUnit

## Démarrage

```bash
./mvnw clean install   # construire et tester
./mvnw spring-boot:run   # démarrer l'application
./mvnw test   # lancer les tests
```

## Docker

```bash
docker build -t movies .
```

## Structure

```
src/
Dockerfile
mvnw
mvnw.cmd
pom.xml
```
