package edu.Biblioteca.Biblioteca1.servicios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


@Service
public class PrestamoServicio {
    @Autowired
    PrestamoRepositorio prestamoRepositorio;

    public List<Prestamo> getAll() {
        List<Prestamo> lista = new ArrayList<Prestamo>();
        prestamoRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Prestamo getById(Long id) {
        return prestamoRepositorio.findById(id).get();
    }

    public void save(Prestamo prestamo) {
        prestamoRepositorio.save(prestamo);
    }

    public void delete(Long id) {
        prestamoRepositorio.deleteById(id);
    }
}
