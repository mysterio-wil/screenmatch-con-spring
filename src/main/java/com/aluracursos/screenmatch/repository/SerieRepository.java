package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Interfaz SerieRepository para gestionar operaciones CRUD en la entidad Serie
// Extiende JpaRepository para aprovechar métodos estándar de JPA como findAll(), save(), deleteById(), etc.
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);
}