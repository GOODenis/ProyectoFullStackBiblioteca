package edu.Biblioteca.Biblioteca1.repositorios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends CrudRepository<Prestamo, Long>{
    @Query("SELECT c FROM Estudiante, Libro c WHERE c.admin.id = ?1")
    //List<Estudiante> findByArea(Long id);

    boolean hasReferences(Long id);

}
