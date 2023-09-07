package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curso {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 100, message= "Nombre demasiado largo")
    @Column(unique = true)
    private String nombre;

    private int meses;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inicio;

    private String imagen;

    private boolean presencial;
    
    // muchos cursos se relacionan con el mismo área
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Area area;
    
    // muchos cursos se relacionan con muchos alumnos
    @ManyToMany
    @JoinTable(name = "alumno_curso",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    private Set<Alumno> alumnos;

    public void agregarAlumno(Alumno alumno) {
        this.getAlumnos().add(alumno);
    }

}