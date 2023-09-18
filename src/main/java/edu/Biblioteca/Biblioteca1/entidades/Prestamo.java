package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPrestamo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDevolucion;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Libro libro;
}