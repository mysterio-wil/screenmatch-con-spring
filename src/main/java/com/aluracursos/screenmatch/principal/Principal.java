package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=66d123fa";
    private ConvierteDatos conversor = new ConvierteDatos();

    // Método para mostrar el menú y buscar la serie
    public void muestraElMenu () {
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar:");
        System.out.print(">>> ");

        // Busca los datos generales de las temporadas
        var nombreSerie = teclado.nextLine(); // Método para mostrar el menú y buscar la serie
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY); // Llama a la API
        var datos = conversor.obtenerDatos(json, DatosSerie.class); // Convierte los datos JSON a objeto DatosSerie
        System.out.println(datos); // Muestra los datos de la serie

        // Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>(); // Lista para almacenar los datos de temporadas
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) { // Itera por cada temporada
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY); // Llama a la API para la temporada
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class); // Convierte los datos JSON a objeto DatosTemporadas
            temporadas.add(datosTemporadas); // Agrega los datos de la temporada a la lista
        }
        // temporadas.forEach(System.out::println); // Muestra los datos de cada temporada

        // Mostrar el número de temporada, número de episodios y lista de episodios enumerada.
        for (int i = 0; i < datos.totalDeTemporadas(); i++) { // Itera sobre cada temporada
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios(); // Obtiene la lista de episodios para la temporada actual
            int numeroTemporada = i + 1; // Se asume que las temporadas comienzan en 1
            System.out.println("Temporada " + numeroTemporada + ": " + episodiosTemporada.size() + " episodios"); // Muestra el número de temporada y episodios
            for (int j = 0; j < episodiosTemporada.size(); j++) { // Itera sobre cada episodio de la temporada
                System.out.println((j + 1) + ". " + episodiosTemporada.get(j).titulo()); // Muestra el título del episodio enumerado
            }
        }
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        IntStream.range(0, temporadas.size()).forEach(i -> {
            var episodiosTemporada = temporadas.get(i).episodios();
            System.out.println("Temporada " + (i + 1) + ": " + episodiosTemporada.size() + " episodios");
            IntStream.range(0, episodiosTemporada.size()).forEach(j ->
                    System.out.println((j + 1) + ". " + episodiosTemporada.get(j).titulo())
            );
        });

        // Convertir todas las informaciones a una lista del tipo DatosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())  // Aplana la lista de episodios de todas las temporadas
                .collect(Collectors.toList());         // Recolecta todos los episodios en una lista

        // Muestra el Top 5 de episodios mejor evaluados
        System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A")) // Filtrar episodios que tengan evaluación disponible
                .peek(e -> System.out.println("Primer filtro (excluyendo N/A): " + e)) // Mostrar cada episodio después del primer filtro
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed()) // Ordenar los episodios por evaluación de mayor a menor
                .peek(e -> System.out.println("Después de ordenar (mayor a menor): " + e)) // Mostrar cada episodio después de ordenar
                .map(e -> e.titulo().toUpperCase()) // Convertir el título de cada episodio a mayúsculas
                .peek(e -> System.out.println("Título en mayúsculas: " + e)) // Mostrar cada título después de la conversión
                .limit(5) // Limitar el resultado a los 5 episodios mejor evaluados
                .forEach(System.out::println); // Imprimir los episodios en la consola

        // Convirtiendo los datos a una lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))  // Mapeo de DatosEpisodio a Episodio
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);  // Imprime cada episodio en la consola

        // Solicitar el año de búsqueda al usuario
        System.out.println("Por favor indique el año a partir del cual desea ver los episodios");
        var fecha = teclado.nextInt(); // Capturar el año proporcionado
        teclado.nextLine(); // Consumir la línea pendiente

        // Crear un objeto LocalDate a partir del año ingresado, con el día 1 de enero
        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

        // Formatear la fecha de lanzamiento a formato dd/MM/yyyy
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Filtrar los episodios que fueron lanzados después de la fecha ingresada
        episodios.stream()
                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda)) // Filtrar por fecha
                .forEach(e -> System.out.println( // Imprimir los episodios filtrados
                        "Temporada " + e.getTemporada() + // Mostrar la temporada
                                " Episodio " + e.getTitulo() + // Mostrar el título del episodio
                                " Fecha de Lanzamiento " + e.getFechaDeLanzamiento().format(dtf) // Mostrar la fecha formateada
                ));

        // Solicitar al usuario una parte del título del episodio que desea buscar
        System.out.println("Por favor escriba el título del episodio que quiere ver: ");
        System.out.print(">>> ");
        var pedazoTitulo = teclado.nextLine(); // Capturar el fragmento de título ingresado

        // Buscar el primer episodio cuyo título contenga el fragmento ingresado (ignora mayúsculas/minúsculas)
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase())) // Comparar en mayúsculas
                .findFirst(); // Obtener la primera coincidencia, si existe

        // Verificar si el episodio fue encontrado y mostrar resultados
        if (episodioBuscado.isPresent()) {
            System.out.println("Episodio encontrado"); // Confirmación de episodio encontrado
            System.out.println("Los datos son: " + episodioBuscado.get()); // Mostrar los datos del episodio encontrado
        } else {
            System.out.println("Episodio no encontrado"); // Mensaje en caso de que no haya coincidencias
        }

        // Filtrar episodios que tengan una evaluación válida (mayor a 0.0) y agrupar por temporada
        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0) // Solo considerar episodios con evaluación mayor a 0
                .collect(Collectors.groupingBy(Episodio::getTemporada, // Agrupar los episodios por número de temporada
                        Collectors.averagingDouble(Episodio::getEvaluacion) // Calcular el promedio de evaluación para cada temporada
                ));

        // Imprimir el mapa con el promedio de evaluaciones por temporada
        System.out.println(evaluacionesPorTemporada);

        // Crear estadísticas resumen de las evaluaciones de episodios con evaluación mayor a 0.0
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0) // Filtrar episodios con evaluación válida
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion)); // Recopilar estadísticas de evaluaciones

// Imprimir estadísticas clave de las evaluaciones
        System.out.println("Media de las evaluaciones: " + est.getAverage()); // Promedio de todas las evaluaciones
        System.out.println("Episodio mejor evaluado: " + est.getMax()); // Evaluación máxima entre episodios
        System.out.println("Episodio peor evaluado: " + est.getMin()); // Evaluación mínima entre episodios
        System.out.println("Cantidad de episodios evaluados: " + est.getCount()); // Total de episodios evaluados


    }
}
