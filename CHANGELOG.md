## [Unreleased]
### Added
- Se agregó la dependencia Spring Web al archivo `pom.xml` para habilitar el desarrollo de API REST.
- Se creó la clase `SerieController` con un endpoint básico `GET /series` que retorna un mensaje introductorio.
- Creado el paquete `dto` y la clase `SerieDTO` para representar los datos principales de las series de forma desacoplada de las entidades.
- Creado el paquete `config` y la clase `CorsConfiguration` para configurar solicitudes CORS desde `http://127.0.0.1:5500` con soporte para múltiples métodos HTTP.
- Añadida la dependencia `spring-boot-devtools` al archivo `pom.xml` para habilitar el reinicio automático del servidor y optimizar el flujo de desarrollo.

### Changed
- Se refactorizó la clase `ScreenmatchApplication` para mejorar la modularidad y la estructura del proyecto.
- Integración del repositorio SerieRepository en el endpoint GET /series para obtener las series de la base de datos.
- Actualizado el controlador `SerieController` para que el endpoint GET /series retorne una lista de `SerieDTO` en lugar de las entidades `Serie`.