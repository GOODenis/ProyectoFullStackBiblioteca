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

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
        libro.setAdmin(this);
    }

    public void quitarLibro(Libro libro) {
        this.libros.remove(libro);
        libro.setAdmin(null);
    }

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private Set<Estudiante> estudiantes = new HashSet<>();

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
        estudiante.setAdmin(this);
    }

    public void quitarEstudiante(Estudiante estudiante) {
        this.estudiantes.remove(estudiante);
        estudiante.setAdmin(null);
    }
}