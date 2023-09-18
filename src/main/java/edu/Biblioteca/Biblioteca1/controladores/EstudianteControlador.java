package edu.Biblioteca.Biblioteca1.controladores;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.servicios.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;





/*
@RestController
@RequestMapping("estudiantes")
public class EstudianteControlador implements WebMvcConfigurer{
    @Autowired
    EstudianteRepositorio estudianteRepositorio;
  
    @Autowired
    EstudianteServicio estudianteServicio;

    @GetMapping
  public ModelAndView index() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Listado de Estudiantes");
    mav.addObject("vista", "estudiantes/index");
    mav.addObject("estudiantes", estudianteServicio.getAll());
    return mav;
  }

  @GetMapping("/lista")
  public List<Estudiante> lista() {
    return estudianteServicio.getAll();
  }

  
  @GetMapping("/crear")
  public ModelAndView crear(Estudiante estudiante) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Cargar estudiante");
    mav.addObject("vista", "estudiante/crear");
    mav.addObject("estudiante", estudiante);
    return mav;
  }

  @PostMapping("/crear")
  public ModelAndView guardar(@Valid Estudiante estudiante, BindingResult br, RedirectAttributes ra) {
    if ( br.hasErrors() ) {
      return this.crear(estudiante);
    }

    estudianteServicio.save(estudiante);

    ModelAndView mav = this.index();
    mav.addObject("exito", "Estudiante cargado exitosamente");
    return mav;
  }

  @GetMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable("id") Long id, Estudiante estudiante) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Editar estudiante");
    mav.addObject("vista", "estudiante/editar");
    mav.addObject("estudiante", estudianteServicio.getById(id));

    return mav;
  }

  @PutMapping("/editar/{id}")
  public ModelAndView actualizar(@PathVariable("id") Long id, @Valid Estudiante estudiante, BindingResult br, RedirectAttributes ra) {
    if ( br.hasErrors() ) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("fragments/base");
      mav.addObject("titulo", "Editar estudiante");
      mav.addObject("vista", "estudiante/editar");
      mav.addObject("estudiante", estudiante);
      return mav;
    }

    Estudiante registro = estudianteServicio.getById(id);
    registro.setNombre(estudiante.getNombre());
    registro.setDireccion(estudiante.getDireccion());
    registro.setDni(estudiante.getDni());
    registro.setEmail(estudiante.getEmail());
    ModelAndView mav = this.index();

    estudianteServicio.save(registro);
    mav.addObject("exito", "Estudiante editada exitosamente");
    return mav;
  }
  @DeleteMapping("/{id}")
public ModelAndView eliminar(@PathVariable("id") Long id) {
    ModelAndView mav;

    if (estudianteRepositorio.existsByPrestamos_EstudianteId(id)) {
        mav = this.index();
        mav.addObject("error", "No se puede borrar el registro porque posee datos asociados");
    } else {
        estudianteServicio.delete(id);
        mav = this.index();
        mav.addObject("exito", "Estudiante eliminada exitosamente");
    }

    return mav;
}
*/
@RestController
@RequestMapping("estudiantes")
public class EstudianteControlador {

    @Autowired
    EstudianteServicio estudianteServicio;
    @Autowired
    EstudianteRepositorio estudianteRepositorio;

    @GetMapping("/lista")
    public List<Estudiante> lista() {
        return estudianteServicio.getAll();
    }

    @GetMapping("ver/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable Long id) {
        Estudiante estudiante = estudianteServicio.getById(id);
        if (estudiante != null) {
            return new ResponseEntity<>(estudiante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@Valid @RequestBody Estudiante estudiante) {
        estudianteServicio.save(estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @Valid @RequestBody Estudiante estudiante) {
        Estudiante estudianteExistente = estudianteServicio.getById(id);
        if (estudianteExistente != null) {
            // Actualizar los campos necesarios
            estudianteExistente.setNombre(estudiante.getNombre());
            estudianteExistente.setDni(estudiante.getDni());
            estudianteExistente.setDireccion(estudiante.getDireccion());
            estudianteExistente.setEmail(estudiante.getEmail());
            estudianteServicio.save(estudianteExistente);
            return new ResponseEntity<>(estudianteExistente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        Estudiante estudiante = estudianteServicio.getById(id);
        if (estudiante != null) {
            estudianteServicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}
