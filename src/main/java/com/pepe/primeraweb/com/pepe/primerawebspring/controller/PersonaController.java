package com.pepe.primeraweb.com.pepe.primerawebspring.controller;

import com.pepe.primeraweb.com.pepe.primerawebspring.entities.Persona;
import com.pepe.primeraweb.com.pepe.primerawebspring.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/personas", "/"})
public class PersonaController {
    @Autowired
    PersonaService personaService;
// la clase model se utiliza para transferir objetos del controller a la vista
    @GetMapping
    public String listarPersonas(Model model){
        List<Persona> personas = personaService.obtenerTodas();
        model.addAttribute("personasLista", personas);//key-valor
        return "listar";

    }
    @GetMapping("/nueva")
    public String formularioNuevaPersona(Model model){
    model.addAttribute("persona", new Persona());//new porque es para añadir otra persona nueva
        model.addAttribute("accion", "/personas/nueva");//su atributo y la accion que hará
        return "form";
    }

    @PostMapping("/nueva")//Con el creas
    public String guardarNuevaPersona(@ModelAttribute Persona persona){//ModelAttribute obtiene lo que hay escrito para guardarlo
    personaService.crearPersona(persona);
    return "redirect:/personas";
    }

    @GetMapping("/editar/{id}")//Al ir a esta ventana se recogerá el id de la persona
    public String formularioEditarPersona(@PathVariable Long id, @ModelAttribute Persona persona, Model model){
        //@PathVariable nos permite acceder a un row de la BBDD por parametros y se usa un modelAttribute para coger el resto de datos
        model.addAttribute("persona", persona);
        model.addAttribute("accion", "/personas/editar/"+id);//su atributo y la accion que hará
        return "form";
    }
    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute Persona persona, Model model){
        personaService.actualizarPersona(id, persona);
        return "redirect:/personas";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id){
        personaService.eliminarPersona(id);
        return "redirect:/personas";

    }

}
