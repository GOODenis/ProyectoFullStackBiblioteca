package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    private String nombre;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 10, message = "DNI demasiado largo")
    private String dni;

    private String direccion;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message = "Email demasiado largo")
    private String email;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos;

    @ManyToOne
    private Admin admin;
}