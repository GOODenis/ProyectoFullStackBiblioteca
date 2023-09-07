package edu.Biblioteca.Biblioteca1.servicios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CursoServicio {

    @Autowired
    CursoRepositorio cursoRepositorio;

    public List<Curso> getAll() {
        List<Curso> lista = new ArrayList<Curso>();
        cursoRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Curso getById(Long id) {
        return cursoRepositorio.findById(id).get();
    }

    public List<Curso> getByArea(Long id) {
        return cursoRepositorio.findByArea(id);
    }

    public void save(Curso curso) {
        cursoRepositorio.save(curso);
    }

    public void delete(Long id) {
        cursoRepositorio.deleteById(id);
    }
    
}
