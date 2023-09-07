package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Area {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message= "Nombre demasiado largo")
    @Column(unique = true)
    private String nombre;
    
    // El área conoce a todos los cursos
    @OneToMany(mappedBy="area", fetch = FetchType.EAGER)
    private Set<Curso> cursos;
    
    public void agregarCurso(Curso curso) {
        this.getCursos().add(curso);
    }
    
    public void quitarCurso(Curso curso) {
        this.getCursos().remove(curso);
    }

}