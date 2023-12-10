# Imagen base de java a usar
FROM openjdk:17
VOLUME /main-app
# Archivo para el contenedor
ADD target/gaticket-0.0.1-SNAPSHOT.jar app.jar
# Puerto se expone
EXPOSE 8080
# Comandos a ejecutar al lanzarlo
ENTRYPOINT ["java", "-jar","/app.jar"]
