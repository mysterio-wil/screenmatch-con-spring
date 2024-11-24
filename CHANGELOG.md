## [Unreleased]

### **Added**
- Se agregó la dependencia `Spring Web` al archivo `pom.xml` para habilitar el desarrollo de API REST.
- Se creó la clase `SerieController` con un endpoint básico `GET /series` que retorna un mensaje introductorio.
- Creado el paquete `dto` y la clase `SerieDTO` para representar los datos principales de las series de forma desacoplada de las entidades.
- Creado el paquete `config` y la clase `CorsConfiguration` para configurar solicitudes CORS desde `http://127.0.0.1:5500` con soporte para múltiples métodos HTTP.
- Añadida la dependencia `spring-boot-devtools` al archivo `pom.xml` para habilitar el reinicio automático del servidor y optimizar el flujo de desarrollo.
- Creada la clase `SerieService` dentro del paquete `service` para manejar la lógica de negocio relacionada con las series.
- Se añadió el campo `Long id` en el record `SerieDTO` para incluir el identificador de la serie.
- Se añadió la query `lanzamientosMasRecientes()` en `SerieRepository` para obtener las series con los lanzamientos más recientes, ordenadas por la fecha de lanzamiento de sus episodios.
- Se añadió el método `obtenerLanzamientosMasRecientes()` en `SerieService` para obtener las series más recientes utilizando el repositorio.
- Se añadió el endpoint `@GetMapping("/lanzamientos")` en `SerieController` para obtener las series con los lanzamientos más recientes, utilizando el método correspondiente de `SerieService`.
- Se añadió el método `obtenerPorId(Long id)` en la clase `SerieService` para obtener los detalles de una serie específica por su ID. Si la serie no existe, retorna `null`.
- Se añadió el endpoint `GET /series/{id}` en `SerieController` para exponer la funcionalidad de obtener los detalles de una serie específica, utilizando el método correspondiente de `SerieService`.
- Se añadió en `SerieRepository` la query `obtenerTemporadasPorNumero` para obtener los episodios de una temporada específica de una serie, utilizando el ID de la serie y el número de temporada.
- Se añadió en `SerieService` los métodos `obtenerTodasLasTemporadas` y `obtenerTemporadasPorNumero`.
- Se añadió en `SerieController` los endpoints `GET /series/{id}/temporadas/todas` y `GET /series/{id}/temporadas/{numeroTemporada}`.
- Se añadió el endpoint `GET /series/categoria/{nombreGenero}` en `SerieController` para obtener series filtradas por género.
- Se añadió el método `obtenerSeriesPorCategoria` en `SerieService` para obtener series por género, utilizando el método `findByGenero` en `SerieRepository`.
- El método `obtenerSeriesPorCategoria` convierte el nombre del género en español a su representación en la clase `Categoria` y devuelve una lista de `SerieDTO` correspondientes.
- Se añadió el endpoint `GET /series/{id}/temporadas/top` en la clase `SerieController`.
    - Este endpoint permite obtener los 5 episodios mejor evaluados de una serie específica mediante su ID.
    - El método delega la lógica de negocio al método `obtenerTopEpisodios` en `SerieService`.
- Se añadió el método `obtenerTopEpisodios(Long id)` en la clase `SerieService`.
    - Este método obtiene los 5 episodios con la mejor evaluación de una serie dada.
    - Utiliza la consulta personalizada `topEpisodiosPorSerie` definida en `SerieRepository`.
- Se añadió la consulta personalizada `topEpisodiosPorSerie` en `SerieRepository`.
    - La consulta utiliza un `JOIN` entre la serie y sus episodios para obtener los 5 episodios mejor evaluados de una serie específica.

### **Changed**
- Se refactorizó la clase `ScreenmatchApplication` para mejorar la modularidad y la estructura del proyecto.
- Integración del repositorio `SerieRepository` en el endpoint `GET /series` para obtener las series de la base de datos.
- Actualizado el controlador `SerieController` para que el endpoint `GET /series` retorne una lista de `SerieDTO` en lugar de las entidades `Serie`.
- Refactorizada la clase `SerieController` para que utilice la clase `SerieService` y delegue la lógica de negocio en lugar de manejarla directamente.
- Actualizada la clase `SerieController` para incluir un nuevo endpoint `GET /series/top5` que retorna las 5 mejores series utilizando el servicio `SerieService`.
- Refactorizado el método `obtenerTodasLasSeries()` en `SerieService` para usar `convierteDatos()`.
- Añadidos los métodos `obtenerTop5()` y `convierteDatos()` en `SerieService`. El primero obtiene las 5 mejores series por evaluación, y el segundo convierte una lista de entidades `Serie` a `SerieDTO`.
- Refactorizada la clase `SerieController` para añadir la anotación `@RequestMapping("/series")` como ruta base para los endpoints relacionados con las series.