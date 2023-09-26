package edu.sistema.biblioteca.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message= "Direccion demasiado largo")
    @Column(unique = true)
    private String direccion;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 50, message = "Email demasiado largo")
    private String email;



    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos = new ArrayList<>();

   // @ManyToMany(mappedBy = "estudiantes")
   // private Set<Administrador> administradores = new HashSet<>();
    @ManyToMany(mappedBy = "estudiantes", fetch = FetchType.EAGER)
    private Set<Administrador> administradores;

}