package edu.Biblioteca.Biblioteca1.servicios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class EstudianteServicio {

    @Autowired
    EstudianteRepositorio estudianteRepositorio;

    public List<Estudiante> getAll() {
        List<Estudiante> lista = new ArrayList<Estudiante>();
        estudianteRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Estudiante getById(Long id) {
        return estudianteRepositorio.findById(id).get();
    }

    public void save(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }

    public void delete(Long id) {
        estudianteRepositorio.deleteById(id);
    }
    
}
