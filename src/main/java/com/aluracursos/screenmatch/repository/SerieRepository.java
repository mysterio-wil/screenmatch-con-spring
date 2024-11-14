package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Interfaz SerieRepository para gestionar operaciones CRUD en la entidad Serie
// Extiende JpaRepository para aprovechar métodos estándar de JPA como findAll(), save(), deleteById(), etc.
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByEvaluacionDesc();

    List<Serie> findByGenero(Categoria categoria);

    // Método personalizado para buscar series por número de temporadas y evaluación
    List<Serie> findByTotalDeTemporadasAndEvaluacion(Double totalDeTemporadas, Double evaluacion);
}