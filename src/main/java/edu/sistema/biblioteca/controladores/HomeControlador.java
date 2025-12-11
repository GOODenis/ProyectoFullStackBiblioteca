package edu.sistema.biblioteca.controladores;

import jakarta.mail.MessagingException;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.sistema.biblioteca.repositorios.*;
import edu.sistema.biblioteca.servicios.*;

import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class HomeControlador {

    @Autowired
    LibroRepositorio libroRepositorio;

    @Autowired
    LibroServicio libroServicio;

    @Autowired
    EmailServicio emailServicio;

    @RequestMapping("/")
    public ModelAndView home()
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Inicio");
        maw.addObject("vista", "inicio/home");
        maw.addObject("libros", libroServicio.getAll());
        String destinatario = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> valores = new HashMap();
        valores.put("nombre", "Lucas");
        valores.put("fecha", "05-09-2023");
        String[] habilidades = {"Programar", "Teclear mal", "Dormir"};
        valores.put("habilidades", habilidades);

        try {
            emailServicio.enviarMailHtml(destinatario, "Prueba de correo", "emails/ejemplo", valores);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return maw;  
    }
    
}