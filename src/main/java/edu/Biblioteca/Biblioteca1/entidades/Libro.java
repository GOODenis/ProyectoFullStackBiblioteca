package edu.Biblioteca.Biblioteca1.entidades;

import java.util.*;
//import org.springframework.format.annotation.DateTimeFormat;
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
    private String titulo;
    private String autor;
    private Long cantidad;


     // muchos libros se relacionan con el mismo admin
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Admin admin;




}
