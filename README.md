# Challenge SSYS #

## Summary ##

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
