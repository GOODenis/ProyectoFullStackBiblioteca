package edu.Biblioteca.Biblioteca1.controladores;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.servicios.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import jakarta.validation.Valid;
import java.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@RestController
@RequestMapping("prestamos")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio prestamoServicio;

    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoServicio.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Long id) {
        Prestamo prestamo = prestamoServicio.getById(id);
        if (prestamo != null) {
            return new ResponseEntity<>(prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@Valid @RequestBody Prestamo prestamo) {
        prestamoServicio.save(prestamo);
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Long id, @Valid @RequestBody Prestamo prestamo) {
        Prestamo prestamoExistente = prestamoServicio.getById(id);
        if (prestamoExistente != null) {
            // Actualizar los campos necesarios
            prestamoExistente.setFechaPrestamo(prestamo.getFechaPrestamo());
            prestamoExistente.setFechaDevolucion(prestamo.getFechaDevolucion());
            prestamoExistente.setEstudiante(prestamo.getEstudiante());
            prestamoExistente.setLibro(prestamo.getLibro());
            prestamoServicio.save(prestamoExistente);
            return new ResponseEntity<>(prestamoExistente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoServicio.getById(id);
        if (prestamo != null) {
            prestamoServicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

  
