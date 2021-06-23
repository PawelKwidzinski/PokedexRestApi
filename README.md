# Pokedex REST API - intership Java exercise
## General
CRUD service with "Pokemon" as model of application. Data are stored in memory H2 database and displaying in JSON format.

## Features:
* CRUD
* Filter by types (TODO)
* Filter by name (TODO)

## Endpoints
### Requests:
* GET `http://localhost:8080/pokemons` - to display all Pokemons
* GET `http://localhost:8080/pokemons/1` - to display Pokemon by id
* POST `http://localhost:8080/pokemons` - to save Pokemon in database
* PUT `http://localhost:8080/pokemons/1` - to update Pokemon by id
* DELETE `http://localhost:8080/pokemons/1` - to delete Pokemon by id

## Technologies
* Java 11
* Maven
* Spring Boot 2
* H2
## Configuration
application.properties file:
* `spring.h2.console.enabledl=`
* `spring.h2.console.path=`
* `spring.datasource.url=`
* `spring.jpa.hibernate.ddl-auto=`
