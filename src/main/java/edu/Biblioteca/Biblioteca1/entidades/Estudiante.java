package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estudiante {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long contraseña;
    private String username;
    
    // muchos estudiantes se relacionan con muchos libros
    @ManyToMany(mappedBy = "estudiante", fetch = FetchType.EAGER)
    private Set<Libro> libros;
    
    public void agregarLibro(Libro libro) {
        this.getLibros().add(libro);
    }

}