package com.pepe.primeraweb.com.pepe.primerawebspring.service.impl;

import com.pepe.primeraweb.com.pepe.primerawebspring.entities.Persona;
import com.pepe.primeraweb.com.pepe.primerawebspring.repository.PersonaRepository;
import com.pepe.primeraweb.com.pepe.primerawebspring.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Gestiona operaciones de negocio importanntes para la app y llama la repositorio por que trabajará con él
public class PersonaServiceImpl implements PersonaService {

    @Autowired//Para traerlo desde el contenedor de Spring
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();//busca todo lo que haya
    }

    @Override
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id).orElse(null);//busca por id si no existe te da null
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);//guarda la persona en la BBDD
    }

    @Override
    public Persona actualizarPersona(Long id, Persona persona) {
       Persona personaBBDD = personaRepository.findById(id).orElse(null);
       if (personaBBDD!=null){
           personaBBDD.setNombre(persona.getNombre());
           personaBBDD.setEdad(persona.getEdad());
           return personaRepository.save(personaBBDD);
       }

        return null;
    }

    @Override
    public void eliminarPersona(Long id) {
    personaRepository.deleteById(id);
    }

    @Override
    public long contarPersonas() {
        return personaRepository.count();
    }
}
