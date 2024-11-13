package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    // Scanner para leer la entrada del usuario desde la consola
    private Scanner teclado = new Scanner(System.in);
    // Objeto para manejar las solicitudes a la API
    private ConsumoAPI consumoApi = new ConsumoAPI();
    // URL base y clave de la API de OMDb
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=66d123fa";
    // Conversor de JSON a objeto de datos
    private ConvierteDatos conversor = new ConvierteDatos();
    // Lista para almacenar los datos de las series buscadas
    private List<DatosSerie> datosSeries = new ArrayList<>();
    // Repositorio para gestionar la persistencia de Series en la base de datos
    private SerieRepository repositorio;
    private List<Serie> series;

    // Constructor que inicializa el repositorio de series
    public Principal(SerieRepository repository) {
        this.repositorio = repository;
    }

    // Método para mostrar el menú y buscar la serie
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            // Menú de opciones para el usuario
            var menu = """
                    1 - Buscar series
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    4 - Buscar series por título
                    5 - Top 5 mejores series
                    0 - Salir.
                    """;
            System.out.println(menu);
            System.out.println("Escriba una opción: ");
            System.out.print(">>> ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpia el buffer del scanner

            switch (opcion) {
                case 1:
                    buscarSeriesWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriesPorTitulo();
                    break;
                case 5:
                    buscarTop5Series();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // Método para obtener los datos de una serie de la API
    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar:");
        System.out.print(">>> ");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        // Convierte el JSON de la API en un objeto de DatosSerie
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    // Método para buscar y mostrar los episodios de una serie específica
    private void buscarEpisodioPorSerie() {
        mostrarSeriesBuscadas();
        System.out.println("Escriba el nombre de la serie para ver sus episodios:");
        System.out.print(">>> ");
        var nombreSerie = teclado.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DatosTemporadas> temporadas = new ArrayList<>();

            // Itera a través de las temporadas de la serie
            for (int i = 1; i <= serieEncontrada.getTotalDeTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&Season=" + i + API_KEY);
                DatosTemporadas datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporadas);
            }
            // Muestra cada temporada obtenida
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);

        }
    }

    // Método para buscar una serie en la web y almacenarla en la lista
    private void buscarSeriesWeb() {
        DatosSerie datos = getDatosSerie();
        // Crea una instancia de Serie a partir de los datos obtenidos y la guarda en la base de datos
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        // datosSeries.add(datos); // Ya no se usa, ahora se persiste directamente
        System.out.println(datos);
    }

    // Método para mostrar todas las series buscadas hasta el momento
    private void mostrarSeriesBuscadas() {
        // Obtiene todas las series desde el repositorio para mostrarlas en orden
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriesPorTitulo() {
        System.out.println("Escriba el nombre de la serie que desea buscar:");
        System.out.print(">>> ");
        var nombreSerie = teclado.nextLine();
        Optional<Serie> serieBuscada = repositorio.findByTituloContainsIgnoreCase(nombreSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("La serie buscada es: " + serieBuscada.get());
        } else {
            System.out.println("Serie no encontrada");
        }
    }

    private void buscarTop5Series() {
        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
        topSeries.forEach(s -> System.out.println("Serie: " + s.getTitulo() + " Evaluacion: " + s.getEvaluacion()));
    }
}