# Documentación de ScreenMatch - Versión 2.0.0

Bienvenido a la documentación de la versión 2.0.0 de ScreenMatch. A continuación, se detallan las características, mejoras, cambios en el sistema y nuevos endpoints introducidos en esta versión.

## Nuevas Funcionalidades y Cambios

### 1. Endpoints Adicionados

#### Endpoint: `GET /series/{id}/temporadas/top`
- **Descripción**: Obtiene los 5 episodios mejor evaluados de una serie específica, utilizando su ID.
- **Parámetros**:
    - `id`: El ID de la serie.
- **Respuesta**: Lista de los 5 episodios con mejor evaluación de la serie.

#### Endpoint: `GET /series/{id}/temporadas/todas`
- **Descripción**: Obtiene todos los episodios de una serie, organizados por temporada.
- **Parámetros**:
    - `id`: El ID de la serie.
- **Respuesta**: Lista de todos los episodios de la serie, organizados por temporada.

#### Endpoint: `GET /series/{id}/temporadas/{numeroTemporada}`
- **Descripción**: Obtiene todos los episodios de una temporada específica de una serie.
- **Parámetros**:
    - `id`: El ID de la serie.
    - `numeroTemporada`: El número de la temporada de la cual obtener los episodios.
- **Respuesta**: Lista de episodios pertenecientes a la temporada indicada.

#### Endpoint: `GET /series/categoria/{nombreGenero}`
- **Descripción**: Obtiene todas las series que pertenecen a una categoría o género específico.
- **Parámetros**:
    - `nombreGenero`: El nombre del género de las series a consultar (por ejemplo, "comedia", "acción").
- **Respuesta**: Lista de todas las series pertenecientes al género solicitado.

### 2. Métodos en la Capa de Servicio (SerieService)

- **Método `obtenerTopEpisodios(Long id)`**:
    - Obtiene los 5 episodios mejor evaluados de una serie, mediante su ID.
    - Este método utiliza una consulta personalizada en el repositorio para obtener los episodios de la serie en orden descendente según su evaluación.

- **Método `obtenerSeriesPorCategoria(String nombreGenero)`**:
    - Obtiene todas las series de una categoría específica, utilizando el nombre del género. Convierte los datos obtenidos de la base de datos a DTOs (Data Transfer Objects) para su correcta exposición en la API.

### 3. Métodos en la Capa de Repositorio (SerieRepository)

- **Consulta personalizada `topEpisodiosPorSerie(Serie serie)`**:
    - Realiza un `JOIN` entre la entidad `Serie` y sus episodios asociados para obtener los 5 episodios mejor evaluados de la serie.
    - Utiliza un filtro para ordenar los episodios por evaluación, limitando el resultado a los 5 episodios mejor calificados.

### 4. Nuevos DTOs (Data Transfer Objects)

- **EpisodioDTO**:
    - Un nuevo DTO creado para representar los episodios de una serie de forma simplificada. Incluye los siguientes campos:
        - `temporada`: El número de la temporada del episodio.
        - `titulo`: El título del episodio.
        - `numeroEpisodio`: El número de episodio dentro de su temporada.

### 5. Cambios en la Base de Datos

- Se actualizaron las consultas para permitir la obtención de los episodios mejor evaluados de una serie.
- Se optimizó la consulta para obtener los episodios por temporada, de modo que ahora se puede consultar por temporada de forma eficiente.

## Cómo Ejecutar la Aplicación

Para ejecutar la aplicación en tu entorno local, sigue estos pasos:

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu-usuario/ScreenMatch.git
   ```

2. Navega al directorio del proyecto:

   ```bash
   cd ScreenMatch
   ```

3. Instala las dependencias (si estás utilizando Maven):

   ```bash
   mvn install
   ```

4. Ejecuta la aplicación:

   ```bash
   mvn spring-boot:run
   ```

5. La aplicación estará disponible en `http://localhost:8080`.

## Notas de la Versión

- Se ha mejorado el rendimiento de las consultas a la base de datos, especialmente al obtener episodios por temporada.
- Se han añadido filtros de búsqueda para obtener episodios mejor evaluados y para consultar series por género.

## Contribuciones

Si deseas contribuir al proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama con tu funcionalidad o corrección.
3. Realiza un commit con una descripción clara de tus cambios.
4. Envía un pull request para que revisemos tus cambios.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

[Enlace a la versión 1.0.0](./README-v1.md) para consultar la documentación de la versión anterior.