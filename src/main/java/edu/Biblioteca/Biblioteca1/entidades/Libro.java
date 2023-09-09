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
public class Libro {
    
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
         
    // muchos libros se relacionan con muchos estudiantes
    @ManyToMany
    @JoinTable(name = "estudiante_libro",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private Set<Estudiante> estudiantes;

    public void agregarEstudiante(Estudiante estudiante) {
        this.getEstudiantes().add(estudiante);
    }

}