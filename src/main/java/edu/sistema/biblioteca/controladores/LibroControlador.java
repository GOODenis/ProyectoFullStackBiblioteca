package edu.sistema.biblioteca.controladores;

import jakarta.validation.Valid;
import java.io.File;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sistema.biblioteca.entidades.*;
import edu.sistema.biblioteca.repositorios.*;
import edu.sistema.biblioteca.servicios.*;

@RestController
@RequestMapping("libros")
public class LibroControlador implements WebMvcConfigurer{

    @Autowired
    AdministradorServicio administradorServicio;

    @Autowired
    LibroServicio libroServicio;

    @GetMapping 
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Listado de libros");
        mav.addObject("vista", "libros/index");
        mav.addObject("libros", libroServicio.getAll());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crear(Libro libro){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Cargar Libro");
        mav.addObject("vista", "libros/crear");
        mav.addObject("libro", libro);
        mav.addObject("administradores", administradorServicio.getAll());
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
    public ModelAndView editar(@PathVariable ("id")Long id, Libro libro){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Editar Libro");
        mav.addObject("vista", "libros/editar");
        mav.addObject("libro", libroServicio.getById(id));
        return mav;
    }
    @PutMapping("/editar/{id}")
    public ModelAndView actualizar(@PathVariable ("id")Long id, @Valid Libro libro, BindingResult br, RedirectAttributes ra) {
        if ( br.hasErrors() ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Editar Libro");
        mav.addObject("vista", "libros/editar");
        mav.addObject("libro", libro);
        return (mav);
      }
      Libro registro = libroServicio.getById(id);
      registro.setTitulo(libro.getTitulo());
      registro.setAutor(libro.getAutor());
      registro.setCantidad(libro.getCantidad());
      ModelAndView mav = this.index();
  
      libroServicio.save(registro);
      mav.addObject("exito", "Área editada exitosamente");
      return mav;
    }

    @DeleteMapping("/{id}")
    public ModelAndView eliminar(@PathVariable("id") Long id) {
      ModelAndView mav;
  
     /* if ( libroServicio.hasReferences(id) ) {
        mav = this.index();
        mav.addObject("error", "No se puede borrar el registro porque posee datos asociados");
      } else {
        */
        libroServicio.delete(id);
        mav = this.index();
        mav.addObject("exito", "Área eliminada exitosamente");
      //}
      return mav;
    }


    /*    @GetMapping("/lista")
    public List<Libro> index() {
        return libroServicio.getAll();
    }

    @GetMapping("/editar/{id}")
    public ResponseEntity<Libro> ver(@PathVariable Long id) {
        Libro libro = libroServicio.getById(id);
        if (libro != null) {
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Libro> crear(@Valid @RequestBody Libro libro) {
        libroServicio.save(libro);
        return new ResponseEntity<>(libro, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Libro> editar(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        Libro libroExistente = libroServicio.getById(id);
        if (libroExistente != null) {
            // Actualizar los campos necesarios
            libroExistente.setTitulo(libro.getTitulo());
            libroExistente.setAutor(libro.getAutor());
            libroExistente.setCantidad(libro.getCantidad());
            libroServicio.save(libroExistente);
            return new ResponseEntity<>(libroExistente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("//{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        Libro libro = libroServicio.getById(id);
        if (libro != null) {
            libroServicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}