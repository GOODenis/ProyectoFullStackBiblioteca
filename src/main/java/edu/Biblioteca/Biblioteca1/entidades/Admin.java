package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // un admin se relacionan con muchos libros
    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER)
    private Set<Libro> libros;
    
    public void agregarLibro(Libro libro) {
        this.getLibros().add(libro);
    }

    public void quitarLibro(Libro libro) {
        this.getLibros().remove(libro);
    }
    
     // un admin se relacionan con muchos estudiantes
     
    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER)
    private Set<Estudiante> estudiantes;
    
    public void agregarEstudiante(Estudiante estudiante) {
        this.getEstudiantes().add(estudiante);
    }

    public void quitarEstudiante(Estudiante estudiante) {
        this.getEstudiantes().remove(estudiante);
    }
    
}