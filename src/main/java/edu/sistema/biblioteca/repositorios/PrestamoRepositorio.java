package edu.sistema.biblioteca.repositorios;

import edu.sistema.biblioteca.entidades.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrestamoRepositorio extends CrudRepository<Prestamo, Long> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}

