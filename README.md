# GATICKET-API

![Java](https://img.shields.io/badge/Java-red?style=for-the-badge&logo=Java&logoColor=white)
![Spring](https://img.shields.io/badge/SpringBoot-green?style=for-the-badge&logo=spring&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-orange?style=for-the-badge&logo=postman&logoColor=white)
![Github](https://img.shields.io/badge/github-black?style=for-the-badge&logo=github&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-green?style=for-the-badge&logo=swagger&logoColor=black)
![PlantUML](https://img.shields.io/badge/PlantUML-orange?style=for-the-badge&logo=PlantUML&logoColor=black)
![Docker](https://img.shields.io/badge/Docker-blue?style=for-the-badge&logo=Docker&logoColor=white)

***

> API generada para poder trabajar junto con la apliación web GaticketWeb y la apliación para dispositivos Android GaticketApp
    
* GaticketWeb: https://github.com/JSenen/GaticketWeb
* GaticketApp: https://github.com/JSenen/GaticketApp

***
## Documentacion
Por medio de Spring Doc se ha documentado los *_endpoint_* de la API para poder comprobar las diferentes posibilidades
que nos ofrece

![Swagger](https://img.shields.io/badge/swagger-green?style=for-the-badge&logo=swagger&logoColor=black) https://jsenen.github.io/Gaticket_API/#/

Igualmente se ha añadido al proyecto la colección _POSTMAN_ utilizada durante su desarrollo.
![Postman](https://img.shields.io/badge/Postman-orange?style=for-the-badge&logo=postman&logoColor=white) https://github.com/JSenen/Gaticket_API/blob/develop/src/main/java/com/juansenen/gaticket/doc/postman/GATICKET.postman_collection.json

## UML ![PlantUML](https://img.shields.io/badge/PlantUML-orange?style=for-the-badge&logo=PlantUML&logoColor=black)

Diseño de la relacion entre clases de toda la Api. 
Por medio de este diseño, se puede conprobar la relacion entre las clases utilizadas en la Api.
Puede comprobarse como se ha realizado una arquitectura de capas, separadas entre si, en las que se desarrolla una comunicación
entre componentes.

* Capa Controller : Define los puntos de acceso y donde se manejan las distintas solicitudes
* Capa Service: Capa que realiza la lógica de las diferentes peticiones
* Capa Repository:La cual se comunica con la base de datos. Realizando las distintas peticiones CRUD
![img.png](https://github.com/JSenen/Gaticket_API/blob/develop/src/main/java/com/juansenen/gaticket/doc/uml/Uml.png)
***

## DOCKER ![Docker](https://img.shields.io/badge/Docker-blue?style=for-the-badge&logo=Docker&logoColor=white)

Para facilitar la puesta en producción de la Api, se ha introducido un fichero Docker File asi como Docker Compose, por medio del
cual se puede generar una imagen Docker de la Api. Como puede observarse en el fichero Docker Compose, se ha creado una 
persistencia para la base de datos MySql. Lo que nos permite mantener los datos en el contenedor generado por la imagen Docker.

```
# Imagen base de java a usar
FROM openjdk:17
VOLUME /main-app
# Archivo para el contenedor
ADD target/gaticket-0.0.1-SNAPSHOT.jar app.jar
# Puerto se expone
EXPOSE 8080
# Comandos a ejecutar al lanzarlo
ENTRYPOINT ["java", "-jar","/app.jar"]

```
```
version: "3.7"

services:
  api_service:
    build: .
    restart: always
    ports:
      - 8080:8080
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql_db:3306/gaticketdb
      SPRING_DATASOURCE_USERNAME: myuser
      SPRING_DATASOURCE_PASSWORD: mypass
    depends_on:
      - mysql_db
    networks:
      - mynetwork
    command: sh -c './wait-for mysql_db:3306 -- mvn spring-boot:run'

  mysql_db:
    image: "mysql:8.0"
    restart: always
    ports:
      - 3306:3306
    environment:
      MYSQL_DATABASE: gaticketdb
      MYSQL_USER: myuser
      MYSQL_PASSWORD: mypass
      MYSQL_ROOT_PASSWORD: root
    networks:
      - mynetwork
    volumes:
      - /Users/JSenen/IdeaProjects/gaticket/sql:/var/lib/mysql

networks:
  mynetwork:
    driver: bridge
```