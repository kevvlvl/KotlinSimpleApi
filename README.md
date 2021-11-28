# A simple Kotlin REST API example

What started as a simple REST Api writen in Kotlin using the Spring reactive framework is an evolving self-learning project where I'm including librairies/notions to meet various needs (tests, reliability, rdbms, etc.)

Please feel free (just like the rest of my projects) to contribute/comment, fork etc.

## Tech stack

- Kotlin
- Latest Spring Boot 2.x
- PostgreSQL running on a docker container
- Spring Reactive
- Gradle package manager
- JUnit5 integration tests using testcontainers
- Cucumber/BDD style tests using the JUnit 5 runner

## Running the app

### Postgresql DB

```
cd docker
docker build -t finance-postgres -f finance.dockerfile .
docker image ls
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=financedb --name finance-postgres finance-postgres
docker container ls
```

### Locally via gradle

```
./gradlew bootRun
```

### Endpoints

```
curl localhost:8080/stocks
```

### Run tests

Make sure you have docker up and running as the tests use containers to spin up a real postgresql instance.

```
./gradlew test
```