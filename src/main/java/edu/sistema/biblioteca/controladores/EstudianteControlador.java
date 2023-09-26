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
import edu.sistema.biblioteca.repositorios.EstudianteRepositorio;
import edu.sistema.biblioteca.servicios.*;


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
    mav.addObject("titulo", "Listado de cursos");
    mav.addObject("vista", "cursos/index");
    mav.addObject("cursos", estudianteServicio.getAll());
    return mav;
  }
/*
@GetMapping("/lista")
public List<Estudiante> lista() {
  return estudianteServicio.getAll();
} */

@GetMapping("/crear")
public ModelAndView crear(Estudiante estudiante) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("fragments/base");
  mav.addObject("titulo", "Crear área");
  mav.addObject("vista", "estudiantes/crear");
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
  mav.addObject("exito", "Área creada exitosamente");
  return mav;
}

@GetMapping("/editar/{id}")
public ModelAndView editar(@PathVariable("id") Long id, Estudiante estudiante) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("fragments/base");
  mav.addObject("titulo", "Editar área");
  mav.addObject("vista", "estudiantes/editar");
  mav.addObject("estudiante", estudianteServicio.getById(id));

  return mav;
}

@PutMapping("/editar/{id}")
public ModelAndView actualizar(@PathVariable("id") Long id, @Valid Estudiante estudiante, BindingResult br, RedirectAttributes ra) {
  if ( br.hasErrors() ) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("fragments/base");
    mav.addObject("titulo", "Editar área");
    mav.addObject("vista", "estudiantes/editar");
    mav.addObject("estudiante", estudiante);
    return mav;
  }

  Estudiante registro = estudianteServicio.getById(id);
  registro.setNombre(estudiante.getNombre());
  ModelAndView mav = this.index();

  estudianteServicio.save(registro);
  mav.addObject("exito", "Área editada exitosamente");
  return mav;
}

@DeleteMapping("/{id}")
public ModelAndView eliminar(@PathVariable("id") Long id) {
  ModelAndView mav;

  if (estudianteRepositorio.hasReferences(id) ) {
    mav = this.index();
    mav.addObject("error", "No se puede borrar el registro porque posee datos asociados");
  } else {
    estudianteServicio.delete(id);
    mav = this.index();
    mav.addObject("exito", "Área eliminada exitosamente");
  }

  return mav;
}
}
