package edu.sistema.biblioteca.controladores;

import edu.sistema.biblioteca.entidades.*;
import edu.sistema.biblioteca.servicios.*;
import edu.sistema.biblioteca.repositorios.*;
import java.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("admins")
public class AdministradorControlador {

    @Autowired
    AdministradorServicio administradorServicio;
@GetMapping 
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Listado de administradors");
        mav.addObject("vista", "administradores/index");
        mav.addObject("administradores", administradorServicio.getAll());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crear(Administrador administrador){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Cargar Administrador");
        mav.addObject("vista", "administradores/crear");
        mav.addObject("administrador", administrador);
        return mav;
    }
    @PostMapping("/crear")
    public ModelAndView guardar(@Valid Administrador administrador, BindingResult br, RedirectAttributes ra) {
      if ( br.hasErrors() ) {
        return this.crear(administrador);
      }
      administradorServicio.save(administrador);

      ModelAndView mav = this.index();
      mav.addObject("exito", "Administrador cargado exitosamente");
      return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable ("id")Long id, Administrador administrador){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Editar Administrador");
        mav.addObject("vista", "administradores/editar");
        mav.addObject("administrador", administradorServicio.getById(id));
        return mav;
    }
    @PutMapping("/editar/{id}")
    public ModelAndView actualizar(@PathVariable ("id")Long id, @Valid Administrador administrador, BindingResult br, RedirectAttributes ra) {
        if ( br.hasErrors() ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Editar Administrador");
        mav.addObject("vista", "administradores/editar");
        mav.addObject("administrador", administrador);
        return (mav);
      }
      Administrador registro = administradorServicio.getById(id);
      registro.setNombre(administrador.getNombre());
      registro.setEmail(administrador.getEmail());
      registro.setPassword(administrador.getPassword());
      ModelAndView mav = this.index();
  
      administradorServicio.save(registro);
      mav.addObject("exito", "Área editada exitosamente");
      return mav;
    }

    @DeleteMapping("/{id}")
    public ModelAndView eliminar(@PathVariable("id") Long id) {
      ModelAndView mav;
  
     /* if ( administradorServicio.hasReferences(id) ) {
        mav = this.index();
        mav.addObject("error", "No se puede borrar el registro porque posee datos asociados");
      } else {
        */
        administradorServicio.delete(id);
        mav = this.index();
        mav.addObject("exito", "Área eliminada exitosamente");
      //}
      return mav;
    }


}
