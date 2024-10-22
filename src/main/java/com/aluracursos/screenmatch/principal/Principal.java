package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=66d123fa";
    private ConvierteDatos conversor = new ConvierteDatos();

    // Método para mostrar el menú y buscar la serie
    public void muestraElMenu () {
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar");

        // Busca los datos generales de las temporadas
        var nombreSerie = teclado.nextLine(); // Método para mostrar el menú y buscar la serie
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY); // Llama a la API
        var datos = conversor.obtenerDatos(json, DatosSerie.class); // Convierte los datos JSON a objeto DatosSerie
        System.out.println(datos); // Muestra los datos de la serie

        // Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>(); // Lista para almacenar los datos de temporadas
        for (int i = 1; i <= datos.totalDeTemporadas() ; i++) { // Itera por cada temporada
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+" ) + "&Season=" + i + API_KEY); // Llama a la API para la temporada
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class); // Convierte los datos JSON a objeto DatosTemporadas
            temporadas.add(datosTemporadas); // Agrega los datos de la temporada a la lista
        }
        // temporadas.forEach(System.out::println); // Muestra los datos de cada temporada

        // Mostrar solo el título de los episodios para las temporadas.
        for (int i = 0; i < datos.totalDeTemporadas(); i++) { // Itera sobre cada temporada
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios(); // Obtiene la lista de episodios para la temporada actual
            for (int j = 0; j < episodiosTemporada.size(); j++) { // Itera sobre cada episodio de la temporada
                System.out.println(episodiosTemporada.get(j).titulo()); // Muestra el título del episodio
            }
        }

    }}
