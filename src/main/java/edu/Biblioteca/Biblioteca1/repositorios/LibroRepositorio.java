package edu.Biblioteca.Biblioteca1.repositorios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Long> {

  @Query("SELECT c FROM Libro c WHERE c.area.id = ?1")
  List<Libro> findByDato(Long id);

}