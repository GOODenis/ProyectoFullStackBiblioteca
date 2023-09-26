package edu.sistema.biblioteca.repositorios;

import edu.sistema.biblioteca.entidades.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Long> {
  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Curso c WHERE c.area.id = ?1")
  boolean hasReferences(Long id);
}
