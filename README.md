# Simple Kotlin REST API example

## Technologies used

- Kotlin
- Latest Spring Boot 2.x
- PostgreSQL
- Spring Reactive
- Gradle package manager
- Cucumber tests + JUnit 5 Runner

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