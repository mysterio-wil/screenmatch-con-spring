## [Unreleased]
### Added
- Se agregó la dependencia Spring Web al archivo `pom.xml` para habilitar el desarrollo de API REST.
- Se creó la clase `SerieController` con un endpoint básico `GET /series` que retorna un mensaje introductorio.

### Changed
- Se refactorizó la clase `ScreenmatchApplication` para mejorar la modularidad y la estructura del proyecto.
- Integración del repositorio SerieRepository en el endpoint GET /series para obtener las series de la base de datos.