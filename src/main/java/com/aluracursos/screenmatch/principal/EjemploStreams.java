package com.aluracursos.screenmatch.principal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class EjemploStreams {
    public void muestraEjemplo () {
        List<String> nombres = Arrays.asList("Luis", "Humberto", "Wilmer", "Miguel", "Raul", "Ceila");

        nombres.stream()
                .sorted()
                .limit(4)
                .filter(n -> n.startsWith("L"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);
    }
}
