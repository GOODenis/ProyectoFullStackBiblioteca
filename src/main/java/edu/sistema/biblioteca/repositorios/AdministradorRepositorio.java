package edu.sistema.biblioteca.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.sistema.biblioteca.entidades.*;
@Repository
public interface AdministradorRepositorio extends CrudRepository<Administrador, Long> {
        
    Administrador findByEmail(String email);

    boolean existsByEmail(String email);
}
