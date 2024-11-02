package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.*;

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

    // Método para mostrar el menú y buscar la serie
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            // Menú de opciones para el usuario
            var menu = """
                    1 - Buscar series.
                    2 - Buscar episodios.
                    3 - Mostrar series buscadas.
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
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporadas> temporadas = new ArrayList<>();

        // Itera a través de las temporadas de la serie
        for (int i = 1; i <= datosSerie.totalDeTemporadas(); i++) {
            var json = consumoApi.obtenerDatos(URL_BASE + datosSerie.titulo().replace(" ", "+") + "&Season=" + i + API_KEY);
            DatosTemporadas datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        // Muestra cada temporada obtenida
        temporadas.forEach(System.out::println);
    }

    // Método para buscar una serie en la web y almacenarla en la lista
    private void buscarSeriesWeb() {
        DatosSerie datos = getDatosSerie();
        datosSeries.add(datos);
        System.out.println(datos);
    }

    // Método para mostrar todas las series buscadas hasta el momento
    private void mostrarSeriesBuscadas() {
        datosSeries.forEach(System.out::println);
    }
}
