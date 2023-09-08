package edu.Biblioteca.Biblioteca1.servicios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class DatoServicio {

    @Autowired
    DatoRepositorio datoRepositorio;

    public List<Dato> getAll() {
        List<Dato> lista = new ArrayList<Dato>();
        datoRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Dato getById(Long id) {
        return datoRepositorio.findById(id).get();
    }

    public void save(Dato dato) {
        datoRepositorio.save(dato);
    }

    public void delete(Long id) {
        datoRepositorio.deleteById(id);
    }
    
}
