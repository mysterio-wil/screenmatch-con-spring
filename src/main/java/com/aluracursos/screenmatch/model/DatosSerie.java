package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosSerie(
        @JsonAlias String titulo,
        @JsonAlias Integer totalDeTemporadas,
        @JsonAlias String evaluaciones
) {
}
