package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Crea una instancia de la clase Principal, que maneja la lógica de búsqueda de series
		Principal principal = new Principal();
		// Llama al método muestraElMenu para iniciar la interacción con el usuario
		principal.muestraElMenu();
	}
}
