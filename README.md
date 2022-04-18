# Challenge SSYS #

This project consists in my first Java API Rest using Spring Boot 2.6.6, Docker and Docker-compose, read the instructions about how to run.

## Summary ##

- [Challenge SSYS](#challenge-ssys)
  - [Summary](#summary)
  - [Overall](#overall)
  - [Use recommendation](#use-recommendation)
  - [Commands](#commands)
    - [Building .jar](#building-jar)
    - [One-liner docker-compose up](#one-liner-docker-compose-up)
    - [Setting down containers](#setting-down-containers)
    - [Run JAR application to Heroku database (DEFAULT)](#run-jar-application-to-heroku-database-default)
    - [Run JAR application to Local database](#run-jar-application-to-local-database)
    - [Run JAR application to Docker database](#run-jar-application-to-docker-database)
  - [Requests samples](#requests-samples)
    - [Create a new employee](#create-a-new-employee)
    - [Get employee by id](#get-employee-by-id)
    - [Put update employee](#put-update-employee)
    - [Patch Update employee](#patch-update-employee)
    - [Hard Delete employee](#hard-delete-employee)
    - [List all employees](#list-all-employees)
    - [Report employee age](#report-employee-age)
    - [Report employee salary](#report-employee-salary)

## Overall ##

- This project contains some interesting things, like you can change your application-{env}.properties, so just create the .jar than run java select wich one you want use;
- It was used docker-compose because exists two docker images, one for [database mysql](docker/db/Dockerfile) and other for run the [Java API](docker/api/Dockerfile) and this docker images can interact directly each other by a network bridge;
- If you want to make requests, just import the [Postman Collection](docs/Api%20Challenge.postman_collection.json) that have variables making easier to use, or also you have too the [curl's requests](#requests-samples);
- Exists one extra route, because if you follow exactly the [patterns of HTTP methods](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT), PUT should be used to update entirely a resource, but also i created an method PATCH, just for update a specific property of resource;
- The authorization is a custom one based on API key, where you need to pass in request header named as Authorization;
- The Heroky deploy was made by CLI because Heroku was having some [troubles related to OAuth](https://www.scmagazine.com/news/cloud-security/threat-actors-that-compromised-two-oauth-integrators-could-potentially-penetrate-cloud-systems%EF%BF%BC) so i can't make an pipeline to deploy my application directly from github when code reach the main branch.

## Use recommendation ##

> To easily run this project we strong recommend to use docker and docker-compose, because this project contains two containers that are managed by docker-compose, those containers communicate between an network bridge, so if you want [run locally](#run-jar-application-to-local-database), you will need change application-local.properties for your database.

## Commands ##

### Building .jar ###

```bash
mvn clean package spring-boot:repackage
```

### One-liner docker-compose up ###

```bash
mvn clean package spring-boot:repackage && docker-compose up -d --build
```

### Setting down containers ###

```bash
docker-compose down
```

### Run JAR application to Heroku database (DEFAULT) ###

```shell
java -jar ./target/app.jar
```

### Run JAR application to Local database ###

```shell
java -jar ./target/app.jar --spring.profiles.active=local
```

### Run JAR application to Docker database ###

```shell
java -jar ./target/app.jar --spring.profiles.active=docker
```

## Requests samples ##

This project contains an [postman collection](docs/Api%20Challenge.postman_collection.json) in docs.
But also i added the **curl** for requested routes.

### Create a new employee ###

```shell
curl --location --request POST 'localhost:8081/employees/' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Berkley",
    "email": "berkley@hotmail.com"   ,
    "department": "globo",
    "salary": 10000.00,
    "birthDate": "29-11-1980"
}'
```

### Get employee by id ###

```shell
curl --location --request GET 'localhost:8081/employees/1' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2'
```

### Put update employee ###

```shell
curl --location --request PUT 'localhost:8081/employees/2' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Wagnelson",
    "email": "dbmaster@yahoo.com",
    "department": "IT",
    "salary": 237842.0,
    "birthDate": "01-01-1998"
}'
```

### Patch Update employee ###

```shell
curl --location --request PATCH 'localhost:8081/employees/2' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "salary": 237842.0
}'
```

### Hard Delete employee ###

```shell
curl --location --request DELETE 'localhost:8081/employees' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2
```

### List all employees ###

```shell
curl --location --request DELETE 'localhost:8081/employees/1' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2'
```

### Report employee age ###

```shell
curl --location --request GET 'localhost:8081/reports/employees/age' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2'
```

### Report employee salary ###

```shell
curl --location --request GET 'localhost:8081/reports/employees/salary' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2'
```
