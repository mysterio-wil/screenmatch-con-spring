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

# Aula 04 

## Lo que aprendimos en esta aula:

- Crear consultas derivadas con JPA. Conocimos el recurso estándar de JPA para realizar búsquedas utilizando palabras clave en los métodos de la clase Repository.
- Comparar streams y búsquedas en la base de datos. Observamos los cambios al usar streams y las derived queries en nuestra aplicación.
- Conocer diversas palabras clave para crear sus métodos. Profundizamos en las palabras clave y en cómo usarlas, reforzando la práctica.
- Discutir los varios tipos de retorno al realizar las búsquedas. Conversamos sobre las diferencias entre devolver una serie, una lista de series o un Optional de series.
- Leer datos dinámicamente y almacenar en un Enum. Vimos cómo hacer coincidir lo que se está escribiendo con un campo en el enum.

# Aula 05

## Lo que aprendimos en esta aula:

- Diferenciar los tipos de consultas de JPA. Vimos que podemos trabajar con consultas derivadas, con consultas nativas usando nativequery y JPQL, que es el lenguaje de búsqueda de JPA.
- Crear métodos totalmente personalizados y más legibles. Observamos que utilizar JPQL puede ayudar en la escritura de métodos más legibles. Para ello, basta con escribir el nombre del método y anotarlo con @Query.
- rofundizar en el lenguaje SQL. Conocimos varias expresiones utilizadas en SQL, como LIKE, ORDER y LIMIT.
- Recuperar información secundaria. Pudimos buscar información relacionada con episodios a partir de la serie, utilizando la función de uniones (JOIN).
- Comparar recursos SQL y Java. Nos dimos cuenta de que, al igual que Java tiene una API de fechas, SQL también tiene su forma de manejar las fechas. En nuestro caso, utilizamos la función YEAR de SQL.