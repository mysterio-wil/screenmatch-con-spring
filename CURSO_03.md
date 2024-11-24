# Curso de Java: creando tu primera API y conectándola al Front End

# Aula 01

## En esta lección, aprendiste cómo:
- **Conectar el back-end con el front-end**. Vimos que el front-end esperaba buscar datos de una url específica, que era localhost:8080, que es donde subimos nuestro servidor.
- **Configurar una aplicación web con Spring Boot**. Conocimos la dependencia starter-web de Spring, que descarga varias otras dependencias y configura automáticamente un servidor en la puerta localhost:8080, o en alguna otra que configuremos en nuestra aplicación.
- **Organizar un proyecto MVC**. Aprendimos cómo estructurar el proyecto en varias capas y cómo conectarlas.
- **Retornar una información en el navegador**. Creamos nuestro primer controller y nuestra primera ruta de la API, utilizando anotaciones como @RestController y @GetMapping.

# Aula 02

## En esta lección, aprendiste cómo:
- **Devolver los datos de nuestra base a la navegadora**. Trabajamos devolviendo los datos de nuestra base en el Controller, debidamente serializados.
- **Tratar la serialización circular**. Vimos los problemas que ocurren al intentar serializar entidades mapeadas de forma bidireccional y cómo resolverlos.
- **Utilizar el patrón DTO**. Para evitar la serialización circular y principalmente para seguir buenas prácticas, creamos nuestros DTOs. Así, nuestros datos se volvieron más seguros y fueron devueltos de forma personalizada.
- **Lidiar con el error de CORS**. Conocimos el error entre la comunicación entre rutas de orígenes diferentes y pudimos tratarlo, creando la clase CorsConfiguration.
- **Configurar el Live Reload**. Para que la aplicación no necesite ser detenida y reiniciada siempre que haya cambios, usamos el Devtools y cambiamos las configuraciones necesarias en Intellij.

# Aula 03

## En esta clase, aprendiste cómo:
- **Hacer el código más limpio y organizado**. Vimos que la única responsabilidad de un controlador es manejar la comunicación y las rutas de la API. Así, no debe contener reglas de negocio. Y para hacer esta división, creamos una clase de servicios, la SerieService.
- **Utilizar buenas prácticas de extracción de métodos**. Aplicamos principios de la orientación a objetos, extrayendo métodos que eran comunes en el código, facilitando el mantenimiento.
- **Crear una url fija para el Controller**. Usamos el @RequestMapping para que todas las urls mapeadas por el controlador de series tengan como prefijo el “/series”.
- **Retornar los datos de una sola serie**. Para buscar una serie, necesitamos que su id sea pasado como parámetro. Conocimos el @PathVariable, que nos ayuda en ese objetivo.