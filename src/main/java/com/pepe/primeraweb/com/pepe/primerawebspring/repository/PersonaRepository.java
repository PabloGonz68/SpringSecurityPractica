package com.pepe.primeraweb.com.pepe.primerawebspring.repository;

import com.pepe.primeraweb.com.pepe.primerawebspring.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Siempre que sea un Repositorio debe ser una interfaz y extenderse de JpaReposiory que incluye el tipo de tabla y el tipo del id
@Repository //Estereotipo por que podrias poner @Component
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
