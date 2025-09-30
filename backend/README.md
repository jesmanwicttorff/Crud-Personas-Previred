# Backend - CRUD Personas

Se usaron las siguientes funcionalidades que nos provee Spring Boot:
- Java 21
- Maven 3.11
- Spring Web
- Spring Data JPA
- Spring Validation

```bash
mvn spring-boot:run
```

Endpoints principales:
- GET /api/personas
- GET /api/personas/{rut}
- POST /api/personas
- PUT /api/personas/{rut}
- DELETE /api/personas/{rut}


Base de Datos MYSQL:  personas_db
Tabla personas
Credenciales:
user:root
contraseña: 

// Creacion de la Tabla:
CREATE TABLE persona ( id BIGINT AUTO_INCREMENT PRIMARY KEY, rut VARCHAR(12) UNIQUE NOT NULL, nombre VARCHAR(100) NOT NULL, apellido VARCHAR(100) NOT NULL, fecha_nacimiento DATE NOT NULL, edad INT, direccion_calle VARCHAR(200), direccion_comuna VARCHAR(100), direccion_region VARCHAR(100) );

// Insert 
INSERT INTO `personas` (`id`, `rut`, `nombre`, `apellido`, `fecha_nacimiento`, `direccion_calle`, `direccion_comuna`, `direccion_region`) VALUES
	('2','22222222-2','María','González','1985-03-20','Calle Los Álamos 123','Puente Alto','Metropolitana'),
	('4','44444444-4','Ana','Torres','1995-07-10','Camino del Inca 789','Ñuñoa','Metropolitana'),
	('5','25.828.869-7','Jhon','Wicttorff','2009-02-03','Alemania','Munich','Dtt Capital'),
	('6','999999999','Luisa','Ortiz','1972-02-19','Caracas','Libertador','Dtt Capital');
/*!40000 ALTER TABLE `personas` ENABLE KEYS*/;