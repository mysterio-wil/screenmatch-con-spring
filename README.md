# ScreenMatch API

Bienvenido a la documentación oficial de **ScreenMatch API**. Este proyecto proporciona una API para gestionar y consultar series, episodios y más. A continuación, encontrarás enlaces a las documentaciones correspondientes a las versiones 1.x y 2.0.0 de la API.

## Enlaces a la Documentación

- [Documentación de la Versión 1.0.0](./docs/README-v1.md)  
  Enlace a la documentación de la versión 1.x de la API, que cubre los endpoints y características de las versiones anteriores.

- [Documentación de la Versión 2.0.0](./docs/README-v2.md)  
  Enlace a la documentación de la versión 2.0.0, que incluye las nuevas características y cambios realizados en la API.

## Descripción del Proyecto

**ScreenMatch API** permite interactuar con una base de datos de series de televisión, proporcionando endpoints para obtener detalles de series, episodios, categorías y mucho más.

## Endpoints disponibles

Los siguientes son algunos de los principales endpoints disponibles en la API:

1. **/series/{id}**  
   Obtener los detalles de una serie específica.

2. **/series/{id}/temporadas/todas**  
   Obtener todas las temporadas y episodios de una serie.

3. **/series/{id}/temporadas/{numeroTemporada}**  
   Obtener los episodios de una temporada específica de una serie.

4. **/series/{id}/temporadas/top**  
   Obtener los 5 episodios mejor evaluados de una serie.

### ¿Cómo usar la API?

1. **Autenticación**:  
   Actualmente, la API no requiere autenticación.

2. **Peticiones**:  
   Los endpoints están disponibles mediante peticiones HTTP GET. Los parámetros requeridos pueden incluir el ID de la serie, el número de temporada, entre otros.

---

¡Gracias por usar ScreenMatch API!

