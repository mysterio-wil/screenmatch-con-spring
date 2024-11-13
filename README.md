# Curso de Java Web: crea aplicaciones utilizando Spring Boot

# Aula 01

## En esta aula, has aprendido sobre:

- Estructura de un Proyecto Spring: Observamos la estructura inicial de un proyecto Spring y discutimos sobre los paquetes, clases y el método run.
- Inferencia de Tipos en Java: Vimos un ejemplo práctico de inferencia de tipos con 'var' en el código Java.
- Consumo de API: Aprendimos a consumir APIs a través del método 'obtenerDatos', que devuelve los datos deseados en formato Json.
- Modularización de Código: Aprendimos la importancia de tener un código modularizado y de fácil mantenimiento.
- Serialización y Deserialización: Aprendimos cómo transformar JSON en clases y cómo esto es útil para el proyecto.
- Creación de Interfaces e Implementación de Métodos: Se demostró la creación de una interfaz con un método genérico que utiliza Generics, así como la implementación de este método en una clase separada.
- Inclusión de Nuevas Dependencias en el Proyecto: Vimos cómo agregar una nueva dependencia al archivo .pom.xml y cómo este proceso es gestionado por Maven.

# Aula 02

## En esta aula, has aprendido sobre:

- APIs y Consultas Detalladas: Descubrimos cómo trabajar con APIs para obtener información detallada y realizar consultas más específicas.
- Uso de Anotaciones @JsonAlias y @JsonIgnoreProperties: Exploramos la importancia de utilizar estas funciones para mapear la API a la aplicación.
- Creación de Métodos para Interacción con el Usuario: Creamos un método para mostrar el menú e interactuar con el usuario, permitiéndoles ingresar el nombre de la serie que desean buscar.
- Manipulación de Datos de una API: Mostramos cómo importar y manipular datos de una API, en este caso, datos de series de televisión.
- Manipulación de Cadenas para Acceder a una API: Observamos cómo manipular cadenas para crear direcciones que la API entenderá y devolverá los datos deseados.
- Introducción a los Lambdas: Conocimos las Expresiones Lambda en Java, también conocidas como funciones anónimas que podemos usar para escribir código más eficiente.

# Aula 03

## En esta aula, has aprendido sobre:

- Introducción a las Funciones Lambda: Hemos aprendido la sintaxis de las funciones lambda en Java y cómo permiten una escritura más concisa.
- Uso de Streams en Java: Obtuvimos una comprensión esencial de los streams, que son flujos de datos en Java, y cómo realizar operaciones encadenadas en ellos.
- Filtrado de Datos: Aprendimos cómo usar la funcionalidad de filtrado en streams para seleccionar solo datos específicos, en este caso, episodios de series de televisión con una calificación específica.
- Manipulación de Fechas: Exploramos cómo convertir cadenas en LocalDates y cómo manejar posibles excepciones que pueden ocurrir en este proceso.
- Manejo de Excepciones: Realizamos el manejo de excepciones específicas, como NumberFormatException y DateTimeParseException, que pueden ocurrir debido a la conversión de datos.

# Aula 04

## En esta aula, has aprendido sobre:

- Uso de la función peek: Se introdujo la función peek en Java, que permite visualizar lo que está sucediendo en cada etapa del Stream, facilitando el proceso de depuración.
- Operaciones Intermedias y Finales: Aprendimos sobre la utilización de operaciones (como map, filter y find) que nos permiten manipular y encontrar datos dentro de un Stream.
- Uso de Contenedores para Datos: Examinamos cómo usar el contenedor Optional para almacenar un episodio dentro de un Stream y evitar referencias nulas.
- Filtrado de Datos: Aprendimos la importancia de filtrar datos adecuados para análisis y cómo esto puede afectar los resultados.
- Uso de DoubleSummaryStatistics: Aprendimos cómo la clase DoubleSummaryStatistics de Java puede ayudar a analizar información, como la calificación más alta, la más baja y la cantidad de evaluaciones en nuestras series.

# Curso de Java: persistencia de datos y consultas con Spring Data JPA

# Aula 01

## Lo que aprendimos en esta aula:

- Buscar varias series en la API. Creamos un bucle que solo se detiene cuando el usuario elige salir del menú, permitiéndote buscar varias series en la API sin detenerte.
- Métodos privados. Vimos que si solo una clase accederá a un método, no es necesario hacerlo público; podemos declararlo como privado. Esto es esencial para el encapsulamiento de nuestras clases.
- Agregar más información a los datos buscados. Revisamos cómo realizar el mapeo entre atributos de la API y atributos de nuestro registro.
- Convertir los datos que provienen de la API a tu propia clase. Creamos nuestra propia clase Serie para representar mejor nuestros datos. Para esto, fue necesario utilizar varios métodos de conversión.
- Utilizar un "if reducido". Utilizamos la clase “OptionalDouble” para manejar valores decimales y sus posibles errores, utilizando los métodos “of” y “orElse”, que se asemejan mucho al código de un if reducido y son muy útiles para evitar que ocurran excepciones.
- Crear un Enum. Nos dimos cuenta de que sería excelente poder categorizar nuestras series por género. Creamos un enum para esto y vimos cómo crear métodos personalizados en enums.
- Consumir la API de ChatGPT. Utilizamos la API de ChatGPT para traducir nuestros datos, agregamos todas las dependencias necesarias y configuramos la clase de consumo.

# Aula 02

## Lo que aprendimos en esta aula:

- Configurar tu entorno de Postgres. Realizamos la instalación de esta base de datos relacional y observamos la diferencia entre bases de datos relacionales y otros tipos de bases de datos, además de crear nuestra base de datos de series en Postgres.
- Preparar tu aplicación para trabajar con bases de datos. Agregamos la dependencia de JPA al pom.xml y las configuraciones de la base de datos en el archivo application.properties.
- Utilizar anotaciones de Hibernate para mapear tus entidades. Utilizamos anotaciones como @Entity, @Transient y @Column en la clase Serie, indicando cómo serían las configuraciones de la tabla correspondiente en la base de datos.
- Manipular interfaces del tipo Repository. Para realizar operaciones básicas en la base de datos, como un CRUD, necesitamos una interfaz del tipo Repository con nuestro tipo de datos. En nuestro caso, creamos la clase “SerieRepository”.
- Inyectar dependencias. Observamos que no podemos instanciar una interfaz del tipo Repository en cualquier lugar. Deben declararse en clases gestionadas por Spring, precedidas de un @Autowired, indicando que se está realizando una inyección de dependencias.
- Trabajar con variables de entorno. Utilizamos variables de entorno para proteger datos sensibles de la conexión con la base de datos y con la API.

# Aula 03

## Lo que aprendimos en esta aula:

- Mapear relaciones entre entidades de JPA. Aprendimos el uso de las anotaciones @OneToMany y @ManyToOne para identificar la relación "uno a muchos" entre series y episodios.
- Conocer diversos tipos de relación: Identificamos cuál era la relación presente en nuestra aplicación, además de tener conocimiento de los varios tipos de relaciones en bases de datos.
- Asociar claves foráneas: Entendimos el concepto de clave foránea, que es cómo la base de datos identifica y configura relaciones entre diferentes tablas.
- Trabajar con los tipos de Cascade: Dado que nuestro flujo de guardado de datos implicaba guardar series y luego episodios, fue necesario configurar esto utilizando el atributo Cascade.
- Identificar cómo se cargan los datos: También trabajamos con el atributo fetch, que trata sobre cargar los datos de manera "perezosa" (lazy) o "ansiosa" (eager).
- Configurar relaciones bidireccionales: Vimos la importancia de las relaciones bidireccionales y permitimos que las modificaciones aparezcan en ambos lados de la relación, haciendo tanto setEpisodios() en Serie como setSerie() en Episodios.