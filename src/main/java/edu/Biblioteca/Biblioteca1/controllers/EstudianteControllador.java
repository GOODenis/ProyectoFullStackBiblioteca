package edu.Biblioteca.Biblioteca1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.Biblioteca.Biblioteca1.servicios.*;
import edu.Biblioteca.Biblioteca1.entidades.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("estudiantes")
public class EstudianteControllador implements WebMvcConfigurer {

    @Autowired
    EstudianteServicio estudianteServicio;

    
    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragments/base");
        mav.addObject("titulo", "Listado de estudiantes");
        mav.addObject("vistas", "alumnos/index");
        mav.addObject("alumnos", estudianteServicio.getAll());
        return mav;
    }

}

