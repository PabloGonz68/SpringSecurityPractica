package com.pepe.primeraweb.com.pepe.primerawebspring;

import com.pepe.primeraweb.com.pepe.primerawebspring.entities.Persona;
import com.pepe.primeraweb.com.pepe.primerawebspring.repository.PersonaRepository;
import com.pepe.primeraweb.com.pepe.primerawebspring.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class SpringPrimeroApplication /*implements CommandLineRunner*/ {


	@Autowired//Inyecta los datos
	private PersonaService personaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringPrimeroApplication.class, args);

	}

/*
	@Override
	public void run(String... args) throws Exception {
		personaService.crearPersona(new Persona(6L, "David", 22));
		personaService.crearPersona(new Persona(7L, "Andrea", 12));
		personaService.crearPersona(new Persona(8L, "Leopoldo", 23));
		personaService.crearPersona(new Persona(9L, "Jeyfer", 28));
		personaService.crearPersona(new Persona(10L, "Victor", 18));

		System.out.println("Número de personas: "+personaService.contarPersonas());

		List<Persona> personas =personaService.obtenerTodas();
		personas.forEach(p -> System.out.println("Su nombre es: "+p.getNombre()) );
	}*/
}
