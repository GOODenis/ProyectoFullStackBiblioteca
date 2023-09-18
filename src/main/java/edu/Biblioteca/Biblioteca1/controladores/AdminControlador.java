package edu.Biblioteca.Biblioteca1.controladores;

import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.servicios.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admins")
public class AdminControlador {

    @Autowired
    AdminServicio adminServicio;

    @GetMapping
    public List<Admin> listarAdmins() {
        return adminServicio.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> obtenerAdminPorId(@PathVariable Long id) {
        Admin admin = adminServicio.getById(id);
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Admin> crearAdmin(@Valid @RequestBody Admin admin) {
        adminServicio.save(admin);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> actualizarAdmin(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        Admin adminExistente = adminServicio.getById(id);
        if (adminExistente != null) {
            // Actualizar los campos necesarios
            adminExistente.setNombre(admin.getNombre());
            adminServicio.save(adminExistente);
            return new ResponseEntity<>(adminExistente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdmin(@PathVariable Long id) {
        Admin admin = adminServicio.getById(id);
        if (admin != null) {
            adminServicio.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
