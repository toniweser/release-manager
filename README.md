# Release Manager - Job interview case study

The project is a Spring Boot backend application that was developed as part of a coding challenge for a job interview as
a freelance developer.

Spoiler: I got the job 😍!

Requirements:

- Time available: 3h
- Spring Boot
- Kotlin

You can find the complete task description in the [requirements.md](requirements.md) file.

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

