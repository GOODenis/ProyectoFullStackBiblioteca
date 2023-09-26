package edu.sistema.biblioteca.entidades;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message= "Nombre demasiado largo")
    @Column(unique = true)
    private String nombre;


    @ManyToOne
    @NotNull
    private Rol rol;

   // @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL)
   //private Set<Libro> libros = new HashSet<>();

    @OneToMany(mappedBy = "administrador", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();
  

    @ManyToMany(mappedBy = "administradores", fetch = FetchType.EAGER)
    private Set<Estudiante> estudiantes = new HashSet<>();
    
    
    
    // Métodos para gestionar libros
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        libro.setAdministrador(this);
    }

    public void quitarLibro(Libro libro) {
        libros.remove(libro);
        libro.setAdministrador(null);
    }

    // Métodos para gestionar estudiantes
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        estudiante.getAdministradores().add(this);
    }

    public void quitarEstudiante(Estudiante estudiante) {
        estudiantes.remove(estudiante);
        estudiante.getAdministradores().remove(this);
    }
    /* 
    @ManyToOne
    @NotNull
    private Rol rol;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    @ManyToMany(mappedBy = "administradores", fetch = FetchType.EAGER)
    private Set<Estudiante> estudiantes = new HashSet<>();

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        libro.setAdmin(this);
    }

    public void quitarLibro(Libro libro) {
        libros.remove(libro);
        libro.setAdmin(null);
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        estudiante.getAdministradores().add(this);
    }

    public void quitarEstudiante(Estudiante estudiante) {
        estudiantes.remove(estudiante);
        estudiante.getAdministradores().remove(this);
    }
    */
}