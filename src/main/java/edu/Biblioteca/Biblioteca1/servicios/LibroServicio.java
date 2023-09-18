package edu.Biblioteca.Biblioteca1.servicios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    public List<Libro> getAll() {
        return libroRepositorio.findAll();
    }

    public Libro getById(Long id) {
        return libroRepositorio.findById(id).orElse(null);
    }

    public void save(Libro libro) {
        libroRepositorio.save(libro);
    }

    public void delete(Long id) {
        libroRepositorio.deleteById(id);
    }
}
