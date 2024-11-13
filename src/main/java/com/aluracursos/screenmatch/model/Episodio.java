package com.aluracursos.screenmatch.model;

import jakarta.persistence.*;

import java.time.DateTimeException;
import java.time.LocalDate;
@Entity
@Table(name = "episodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzamiento;
    @ManyToOne
    private Serie serie;

    public Episodio() {}

    // Constructor que inicializa el episodio con los datos recibidos y maneja excepciones
    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(d.evaluacion()); // Conversión segura de evaluación
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0; // Asigna 0 si hay error en la conversión de evaluación
        }
        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento()); // Conversión segura de fecha
        } catch (DateTimeException e) {
            this.fechaDeLanzamiento = null; // Asigna null si hay error en la conversión de fecha
        }
    }

    // Getters y setters para los atributos

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    // Método toString para mostrar la información del episodio
    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                        ", titulo='" + titulo + '\'' +
                        ", numeroEpisodio=" + numeroEpisodio +
                        ", evaluacion=" + evaluacion +
                        ", fechaDeLanzamiento=" + fechaDeLanzamiento;
    }
}