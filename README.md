# Biblioteca
Ejercicio de Biblioteca

## Ejercicio a realizar:
Nos llamaron de la biblioteca Nacional y nos solicitaron construir un sistema para dicha biblioteca donde cumpla con los siguientes casos de usos, estos deben permitir realizar las siguientes funcionalidades:
1.	Incorporar libros y que los mismos no estén duplicados por el título.
2.	Dado una categoría permita mostrar los libros de esa categoría.
3.	Un estudiante pueda solicitar prestado un libro y que su fecha de devolución sea dentro de los siguientes 15 días, teniendo presente que exista un libro disponible.
4.	El usuario de este aplicativo pueda visualizar los libros que se encuentran prestados y su fecha de devolución.
5.	Renovación del préstamo de su libro extendiendo su fecha de devolución siempre y cuando no haya excedido el límite establecido de renovaciones establecidos por la biblioteca.
## Importante
1.	Vamos a construir esta aplicación volcándonos en la metodología TDD (Test Driven Development) “Desarrollo orientado a test” que corresponde a una forma diferente en desarrollar nuestros productos.
Link: https://ed.team/cursos/tdd .
2.	Vamos a trabajar con Junit que es una librería de java para desarrollar nuestros test. Para mas información se puede visitar la docu oficial: https://junit.org/junit5/docs/current/user-guide/
3.	Vamos a generar un proyecto Maven, info sobre maven: https://maven.apache.org/guides/index.html


## Modificación de ejercicio

Nos llamaron de la biblioteca Nacional y nos solicitaron construir un sistema para dicha biblioteca donde cumpla con los siguientes casos de usos, estos deben permitir realizar las siguientes funcionalidades: 

1. Incorporar un libro a la biblioteca. Cada libro tiene un título, un código ISBN y no puede estar registrado más de una vez. 
2. Dado un código ISBN, buscar y devolver los datos del libro identificado por dicho ISBN. Título, código ISBN, autor, y categoría. 
3. Dado una categoría, permitir listar todos los libros correspondientes a esa categoría. 
4. Incorporar un estudiante o socio a la biblioteca registrando un tipo y número de documento, nombre y apellido, y domicilio. 
5. Un estudiante solicita prestado un libro y su plazo límite de devolución es dentro de 15 días corridos, teniendo presente que exista un libro disponible. 
6. Consultar una lista de libros que se encuentran prestados y su fecha de vencimiento del préstamo. 
7. Renovación del préstamo de su libro extendiendo su fecha de devolución siempre y cuando no haya excedido el límite de renovaciones establecido por la biblioteca. 




## Notas Ejecución 

### Docker en Ubuntu
systemctl --user start docker-desktop
Desde la terminal: 
1. docker compose -f docker_compose.yaml up -d
2. docker exec -it tools-postgres-1 bash

### Docker en Windows
Desde powershell: 
1. docker-compose-v1.exe -f .\docker_compose.yaml up -d
2. docker exec -it tools_postgres_1 bash

Luego ya conectado en docker. Para crear y poblar tablas . 
3. sh /docker-entrypoint-initdb.d/after_create.sh 


## Links Interesantes asociados al desarrollo
1. Generación de claves autogeneradas en postgres, usando SERIAL: https://stackoverflow.com/questions/11825643/configure-jpa-to-let-postgresql-generate-the-primary-key-value
2. 