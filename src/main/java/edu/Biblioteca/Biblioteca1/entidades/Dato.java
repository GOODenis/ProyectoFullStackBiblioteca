package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dato {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message= "Nombre demasiado largo")
    @Column(unique = true)
    private String nombre;
    
    // El área conoce a todos los estudiantes
    @OneToOne(mappedBy="dato", fetch = FetchType.EAGER)
    private Set<Estudiante> estudiantes;
    
    public void agregarEstudiante(Estudiante estudiante) {
        this.getEstudiantes().add(estudiante);
    }
    
    public void quitarEstudiante(Estudiante estudiante) {
        this.getEstudiantes().remove(estudiante);
    }

}