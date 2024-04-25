package com.pepe.primeraweb.com.pepe.primerawebspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tblpersonas")//da nombre a la tabla
//Lombok
@Data//para getters y setters
@AllArgsConstructor//constructor con argumentos
@NoArgsConstructor//constructor sin argumentos

public class Persona {
    @Id//Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//AUTOINCREMENTAL
    private Long id;

    private String nombre;

    private int edad;
}
