package edu.Biblioteca.Biblioteca1.repositorios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoRepositorio extends CrudRepository<Dato, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Libro c WHERE c.area.id = ?1")
    boolean hasReferences(Long id);

}