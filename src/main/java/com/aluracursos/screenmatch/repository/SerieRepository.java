package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// Interfaz SerieRepository para gestionar operaciones CRUD en la entidad Serie
// Extiende JpaRepository para aprovechar métodos estándar de JPA como findAll(), save(), deleteById(), etc.
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByEvaluacionDesc();

    List<Serie> findByGenero(Categoria categoria);

    // Método de búsqueda en el repositorio que filtra las series con un número de temporadas menor o igual al especificado
    // y una evaluación mayor o igual al valor dado.
    // List<Serie> findByTotalDeTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalDeTemporadas, Double evaluacion);

    @Query(value = "SELECT * FROM series WHERE series.total_de_temporadas <= 6 AND series.evaluacion >= 7.5", nativeQuery = true)
    List<Serie> seresPorTemporadaYEvaluacion();
}