# Challenge SSYS #

## Summary ##

- [Challenge SSYS](#challenge-ssys)
  - [Summary](#summary)
  - [Commands](#commands)
    - [Building .jar](#building-jar)
    - [Building .jar and running containers](#building-jar-and-running-containers)
    - [Setting down containers](#setting-down-containers)
  - [Requests samples](#requests-samples)
    - [Create a new employee](#create-a-new-employee)
    - [Get employee by id](#get-employee-by-id)
    - [List all employees](#list-all-employees)
    - [Report employee age](#report-employee-age)
    - [Report employee salary](#report-employee-salary)

## Commands ##

```bash
./mvnw pacote && java -jar target/gs-spring-boot-docker-0.1.0.jar
```

```bash
docker-compose --file docker-compose-scale.yml up -d --build
```

### Building .jar ###

```bash
mvn clean package spring-boot:repackage
```

### Building .jar and running containers ###

```bash
mvn clean package spring-boot:repackage && docker-compose up -d --build
```

### Setting down containers ###

```bash
docker-compose down
```

## Requests samples ##

This project contains an [postman collection](docs/Api%20Challenge.postman_collection.json) in docs.
But also i added the **curl** for requested routes.

### Create a new employee ###

```shell
curl --location --request POST 'localhost:8080/employees/' \
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
curl --location --request GET 'localhost:8080/employees/1' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2' \
```

### List all employees ###

```shell
curl --location --request GET 'localhost:8080/employees/list' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2' \
```

### Report employee age ###

```shell
curl --location --request GET 'localhost:8080/reports/employees/age' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2'
```

### Report employee salary ###

```shell
curl --location --request GET 'localhost:8080/reports/employees/salary' \
--header 'Authorization: 21fd2eb403794394ef66db42d12983d2'
```
