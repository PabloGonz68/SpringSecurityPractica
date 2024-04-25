package com.pepe.primeraweb.com.pepe.primerawebspring.service;

import java.util.List;
import com.pepe.primeraweb.com.pepe.primerawebspring.entities.Persona;

public interface PersonaService {
List<Persona> obtenerTodas();
Persona obtenerPorId(Long id);
Persona crearPersona(Persona persona);
Persona actualizarPersona(Long id, Persona persona);
void eliminarPersona(Long id);
long contarPersonas();
}
