package edu.Biblioteca.Biblioteca1.servicios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AlumnoServicio {

    @Autowired
    AlumnoRepositorio alumnoRepositorio;

    public List<Alumno> getAll() {
        List<Alumno> lista = new ArrayList<Alumno>();
        alumnoRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Alumno getById(Long id) {
        return alumnoRepositorio.findById(id).get();
    }

    public void save(Alumno alumno) {
        alumnoRepositorio.save(alumno);
    }

    public void delete(Long id) {
        alumnoRepositorio.deleteById(id);
    }
    
}
