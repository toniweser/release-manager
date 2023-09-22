# Release Manager

## Prerequisites

1. docker, docker-compose
2. JDK 17
3. Maven

## Getting started

The project contains a simple Spring Boot Application using Kotlin with a REST API.

For the local production setup, we use a MySQL database running in a docker container and for the test setup, we use a
H2 in memory database.

### Start app in IDE

1. Build the project using Maven

```
make build
```

2. Start the MySQL database using docker compose

```
make service-env
```

3. Start the application in your IDE (ReleaseManagerApplication.kt)

