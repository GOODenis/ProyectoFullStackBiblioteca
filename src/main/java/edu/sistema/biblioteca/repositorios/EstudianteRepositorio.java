package edu.sistema.biblioteca.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.sistema.biblioteca.entidades.*;

@Repository
public interface EstudianteRepositorio extends CrudRepository<Estudiante, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Curso c WHERE c.area.id = ?1")
    boolean hasReferences(Long id);

}