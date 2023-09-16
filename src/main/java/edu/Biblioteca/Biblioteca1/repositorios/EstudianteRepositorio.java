package edu.Biblioteca.Biblioteca1.repositorios;

import edu.Biblioteca.Biblioteca1.entidades.*;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepositorio extends CrudRepository<Estudiante, Long>{
    @Query("SELECT c FROM Estudiante c WHERE c.libro.id = ?1")
    List<Estudiante> findByeEstudiantes(Long id);

    boolean hasReferences(Long id);

}
