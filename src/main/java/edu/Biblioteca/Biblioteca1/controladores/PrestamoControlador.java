package edu.Biblioteca.Biblioteca1.controladores;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.servicios.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import jakarta.validation.Valid;
import java.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("prestamos")
public class PrestamoControlador implements WebMvcConfigurer {
    @Autowired
    PrestamoRepositorio prestamoRepositorio;
  
    @Autowired
    PrestamoServicio prestamoServicio;

    @GetMapping
  public ModelAndView index() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Listado de Prestamos");
    mav.addObject("vista", "prestamos/index");
    mav.addObject("prestamos", prestamoServicio.getAll());
    return mav;
  }

  @GetMapping("/lista")
  public List<Prestamo> lista() {
    return prestamoServicio.getAll();
  }

  
  @GetMapping("/crear")
  public ModelAndView crear(Prestamo prestamo) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Cargar prestamo");
    mav.addObject("vista", "prestamo/crear");
    mav.addObject("prestamo", prestamo);
    return mav;
  }

  @PostMapping("/crear")
  public ModelAndView guardar(@Valid Prestamo prestamo, BindingResult br, RedirectAttributes ra) {
    if ( br.hasErrors() ) {
      return this.crear(prestamo);
    }

    prestamoServicio.save(prestamo);

    ModelAndView mav = this.index();
    mav.addObject("exito", "Prestamo cargado exitosamente");
    return mav;
  }

  @GetMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable("id") Long id, Prestamo prestamo) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Editar prestamo");
    mav.addObject("vista", "prestamo/editar");
    mav.addObject("prestamo", prestamoServicio.getById(id));

    return mav;
  }

  @PutMapping("/editar/{id}")
  public ModelAndView actualizar(@PathVariable("id") Long id, @Valid Prestamo prestamo, BindingResult br, RedirectAttributes ra) {
    if ( br.hasErrors() ) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("fragments/base");
      mav.addObject("titulo", "Editar prestamo");
      mav.addObject("vista", "prestamo/editar");
      mav.addObject("prestamo", prestamo);
      return mav;
    }

    Prestamo registro = prestamoServicio.getById(id);
    registro.setFechaPrestamo(prestamo.getFechaPrestamo());
    registro.setFechaDevolucion(prestamo.getFechaDevolucion());

    ModelAndView mav = this.index();

    prestamoServicio.save(registro);
    mav.addObject("exito", "Prestamo editada exitosamente");
    return mav;
  }

  @DeleteMapping("/{id}")
  public ModelAndView eliminar(@PathVariable("id") Long id) {
    ModelAndView mav;

    if (prestamoRepositorio.hasReferences(id) ) {
      mav = this.index();
      mav.addObject("error", "No se puede borrar el registro porque posee datos asociados");
    } else {
      prestamoServicio.delete(id);
      mav = this.index();
      mav.addObject("exito", "Prestamo eliminada exitosamente");
    }

    return mav;
  }
    

}
