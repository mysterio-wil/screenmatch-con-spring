# Documentación de la V1 de la Aplicación Screenmatch

## Descripción del Proyecto

Screenmatch es una aplicación para gestionar una base de datos de series, donde los usuarios pueden buscar series por título, género y otros criterios. La aplicación utiliza Spring Boot y PostgreSQL para manejar la persistencia de datos, y tiene integraciones con OpenAI para traducción automática de textos.

## Funcionalidades

### 1. Gestión de Series
- **CRUD de Series**: Permite realizar operaciones CRUD sobre las series almacenadas en la base de datos.
- **Buscar Series**: Las series pueden ser buscadas por su título, género o evaluación.
- **Episodios**: Cada serie tiene una lista de episodios, que se pueden consultar individualmente.

### 2. Consultas Específicas
- Buscar series por número de temporadas y evaluación.
- Obtener las mejores series según su evaluación.
- Buscar episodios por nombre de manera parcial.

### 3. Traducción Automática
- **Integración con OpenAI**: La aplicación incluye una funcionalidad que utiliza la API de OpenAI para traducir textos al español automáticamente.

## Estructura del Proyecto

La aplicación está organizada en varias capas:

1. **Controlador (`Controller`)**: Gestiona las solicitudes HTTP de los usuarios.
2. **Servicio (`Service`)**: Contiene la lógica de negocio, como la gestión de series y episodios.
3. **Repositorio (`Repository`)**: Realiza las operaciones CRUD sobre la base de datos utilizando Spring Data JPA.
4. **Modelo (`Model`)**: Define las entidades `Serie` y `Episodio`.

## Componentes Principales

### 1. `ScreenmatchApplication.java`
Es la clase principal de la aplicación que arranca el servidor Spring Boot. Esta clase implementa `CommandLineRunner`, lo que permite ejecutar código al inicio de la aplicación.

### 2. `SerieRepository.java`
Repositorio que extiende de `JpaRepository` para acceder a las series en la base de datos. Permite realizar búsquedas personalizadas usando JPQL, como encontrar series por título o género.

### 3. `ConsultaChatGPT.java`
Servicio que interactúa con la API de OpenAI para realizar traducciones automáticas de texto.

### 4. `ConsumoAPI.java`
Clase encargada de realizar peticiones HTTP a APIs externas y obtener los datos en formato JSON.

### 5. `ConvierteDatos.java`
Servicio que convierte datos en formato JSON a objetos Java utilizando Jackson.

### 6. `application.properties`
Configuración de la base de datos (PostgreSQL) y de Hibernate. Incluye la configuración de las credenciales de la base de datos y los parámetros para mostrar las consultas SQL en consola.

### 7. `pom.xml`
Gestiona las dependencias del proyecto, incluyendo Spring Boot, JPA, PostgreSQL, Jackson, y la integración con la API de OpenAI.

## Dependencias Utilizadas

- **Spring Boot**: Framework para la construcción de aplicaciones web.
- **Spring Data JPA**: Implementación de JPA para manejar la persistencia de datos.
- **PostgreSQL**: Base de datos relacional utilizada para almacenar las series y episodios.
- **OpenAI GPT-3**: API utilizada para realizar traducciones automáticas de texto.
- **Jackson**: Librería para convertir objetos Java a JSON y viceversa.

## Configuración de la Base de Datos

La aplicación utiliza PostgreSQL como sistema de gestión de base de datos. La configuración de la base de datos se realiza a través de las propiedades en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
```

## Ejecución de la Aplicación

Para ejecutar la aplicación, sigue los siguientes pasos:

1. Asegúrate de tener PostgreSQL instalado y configurado.
2. Configura las variables de entorno `DB_HOST`, `DB_NAME`, `DB_USER` y `DB_PASSWORD`.
3. Compila y ejecuta la aplicación utilizando Maven:
   ```bash
   mvn spring-boot:run
   ```

## Pruebas

Las pruebas están implementadas utilizando el framework de pruebas de Spring Boot. Se incluyen pruebas unitarias para las funcionalidades clave, como la gestión de series y la interacción con la API de OpenAI.

## Licencia

Este proyecto está bajo la Licencia MIT.