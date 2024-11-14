package com.aluracursos.screenmatch.model;

public enum Categoria {
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen");

    private String categoriaOmdb;
    private String categoriaEspanol;
    Categoria(String categoriaOmdb, String categoriaEspanol) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Categoria fromString(String text) {
        // Verifica si el texto es nulo o vacío para evitar errores
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("El texto proporcionado es nulo o vacío");
        }

        // Recorre todas las constantes del enum Categoria
        for (Categoria categoria : Categoria.values()) {
            // Compara el valor de categoriaOmdb con el texto proporcionado, ignorando mayúsculas
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria; // Retorna la constante que coincide
            }
        }

        // Lanza una excepción si no se encuentra ninguna coincidencia
        throw new IllegalArgumentException("Ninguna categoría encontrada: " + text);
    }

    public static Categoria fromEspanol(String text) {
        // Verifica si el texto es nulo o vacío para evitar errores
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("El texto proporcionado es nulo o vacío");
        }

        // Recorre todas las constantes del enum Categoria
        for (Categoria categoria : Categoria.values()) {
            // Compara el valor de categoriaOmdb con el texto proporcionado, ignorando mayúsculas
            if (categoria.categoriaEspanol.equalsIgnoreCase(text)) {
                return categoria; // Retorna la constante que coincide
            }
        }

        // Lanza una excepción si no se encuentra ninguna coincidencia
        throw new IllegalArgumentException("Ninguna categoría encontrada: " + text);
    }
}