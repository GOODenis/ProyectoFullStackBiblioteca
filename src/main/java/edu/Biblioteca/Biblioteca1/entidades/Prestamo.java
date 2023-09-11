package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;
import jakarta.persistence.*;
//import jakarta.util.Date;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
}