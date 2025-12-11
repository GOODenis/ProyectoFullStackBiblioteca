package edu.sistema.biblioteca.entidades;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message= "Nombre demasiado largo")
    @Column(unique = true)
    private String titulo;
    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message= "Nombre demasiado largo")
    @Column(unique = true)
    private String autor;
    private Long cantidad;
    private String imagen;

    /* 
    @ManyToOne
    private Administrador administrador;

    @ManyToMany(mappedBy = "libros", fetch = FetchType.EAGER)
    private Set<Administrador> administradores = new HashSet<>();

     // muchos libros se relacionan con muchos alumnos
    @ManyToMany
    @JoinTable(name = "administrador_libro",
        joinColumns = @JoinColumn (name = ("libro_id")),
        inverseJoinColumns = @JoinColumn (name = "alumno_id")
    )
    private Set<Libro> libros;

    
    public void agregarLibro(Libro libro) {
        this.getLibros().add(libro);
    }
    
    public void quitarLibro(Libro libro) {
        this.getLibros().remove(libro);
    }
    */
    @ManyToOne
    private Administrador administrador;

    @ManyToMany(mappedBy = "libros")
    private Set<Estudiante> estudiantes = new HashSet<>();
}