# Pokedex REST API - intership Java exercise
#### Main points (MUST HAVE)
* use Spring Boot to create a REST API -                  DONE
* getAll and getById                                      DONE
* add, delete and update pokemon                          DONE
* use database to store data about pokemon                DONE
* create readme file in repository, and explain your api  DONE
#### there Aditional points
* clean RESTful api                                       DONE?
* create tests                                            DONE
* search pokemons by types, name                          DONE
* pagination                                              DONE
* authentication                                          NOT DONE
* create api documentation (Swagger/RestDocs)             DONE
* error handling                                          DONE?
## General
Application with "Pokemon" as model displaying in JSON format. Production data is stored in a MySQL database and test data in an H2 database. Unit and integration tests using Mockito and JUnit5 were performed.

## Main Features
* CRUD
* Filter by types 
* Search by name
## Endpoints
![alt text](https://github.com/PawelKwidzinski/PokedexRestApi/blob/master/pr_scr/Swagger.JPG)
## Technologies
* Java 11
* Maven
* Spring Boot 2.4.3
* Spring Data
* H2 (test database)
* MySql (prod database)
* Swagger 2.9.2
## Tests
![alt text](https://github.com/PawelKwidzinski/PokedexRestApi/blob/master/pr_scr/serviceTest.JPG)
![alt text](https://github.com/PawelKwidzinski/PokedexRestApi/blob/master/pr_scr/ControllerTest.JPG)
## Configuration
#### application.properties file:
* `spring.jpa.show-sql=`
#### application-prod.properties file:
* `spring.datasource.url=`
* `spring.datasource.username=`
* `spring.datasource.password=`
* `spring.jpa.hibernate.ddl-auto=`
* `spring.datasource.driver-class-name=`
* `spring.jpa.hibernate.ddl-auto=`
* `spring.jpa.show-sql=`
#### application-test.properties file:
* `spring.h2.console.enabled=`
* `spring.h2.console.path=`
* `spring.datasource.url=`
* `spring.datasource.username=`
* `spring.datasource.password=`
* `spring.datasource.initialization-mode=`
* `spring.datasource.data=`
* `spring.jpa.hibernate.ddl-auto=`
* `spring.jpa.database-platform=`
