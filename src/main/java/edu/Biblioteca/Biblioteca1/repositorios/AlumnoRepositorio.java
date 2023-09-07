package edu.Biblioteca.Biblioteca1.repositorios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepositorio extends CrudRepository<Alumno, Long>{
    
}
