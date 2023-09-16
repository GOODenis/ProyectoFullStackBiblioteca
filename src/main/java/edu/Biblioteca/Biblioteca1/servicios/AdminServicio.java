package edu.Biblioteca.Biblioteca1.servicios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AdminServicio {

    @Autowired
    LibroRepositorio libroRepositorio;

    public List<Libro> getAll() {
        List<Libro> lista = new ArrayList<Libro>();
        libroRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Libro getById(Long id) {
        return libroRepositorio.findById(id).get();
    }

    public List<Libro> getByLibros(Long id) {
        return libroRepositorio.findByLibros(id);
    }

    public void save(Libro libro) {
        libroRepositorio.save(libro);
    }

    public void delete(Long id) {
        libroRepositorio.deleteById(id);
    }
    
}
