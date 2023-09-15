package edu.Biblioteca.Biblioteca1.controladores;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.servicios.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import jakarta.validation.Valid;
import java.io.File;
import java.util.*;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("libros")
public class LibroControlador implements WebMvcConfigurer {

    @Autowired
    LibroRepositorio libroRepositorio;
  
    @Autowired
    LibroServicio libroServicio;

    @GetMapping
  public ModelAndView index() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Listado de Libros");
    mav.addObject("vista", "libros/index");
    mav.addObject("libros", libroServicio.getAll());
    return mav;
  }

  @GetMapping("/lista")
  public List<Libro> lista() {
    return libroServicio.getAll();
  }

  
  @GetMapping("/crear")
  public ModelAndView crear(Libro libro) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Crear libro");
    mav.addObject("vista", "libros/crear");
    mav.addObject("libro", libro);
    return mav;
  }

  @PostMapping("/crear")
  public ModelAndView guardar(@Valid Libro libro, BindingResult br, RedirectAttributes ra) {
    if ( br.hasErrors() ) {
      return this.crear(libro);
    }

    libroServicio.save(libro);

    ModelAndView mav = this.index();
    mav.addObject("exito", "Libro cargado exitosamente");
    return mav;
  }

  @GetMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable("id") Long id, Libro libro) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Editar libro");
    mav.addObject("vista", "libros/editar");
    mav.addObject("libro", libroServicio.getById(id));

    return mav;
  }

  @PutMapping("/editar/{id}")
  public ModelAndView actualizar(@PathVariable("id") Long id, @Valid Libro libro, BindingResult br, RedirectAttributes ra) {
    if ( br.hasErrors() ) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("fragments/base");
      mav.addObject("titulo", "Editar libro");
      mav.addObject("vista", "libros/editar");
      mav.addObject("libro", libro);
      return mav;
    }

    Libro registro = libroServicio.getById(id);
    registro.setTitulo(libro.getTitulo());
    registro.setAutor(libro.getAutor());
    registro.setCantidad(libro.getCantidad());
    ModelAndView mav = this.index();

    libroServicio.save(registro);
    mav.addObject("exito", "Libro editada exitosamente");
    return mav;
  }

  @DeleteMapping("/{id}")
  public ModelAndView eliminar(@PathVariable("id") Long id) {
    ModelAndView mav;

    if (libroRepositorio.hasReferences(id) ) {
      mav = this.index();
      mav.addObject("error", "No se puede borrar el registro porque posee datos asociados");
    } else {
      libroServicio.delete(id);
      mav = this.index();
      mav.addObject("exito", "Libro eliminada exitosamente");
    }

    return mav;
  }

}