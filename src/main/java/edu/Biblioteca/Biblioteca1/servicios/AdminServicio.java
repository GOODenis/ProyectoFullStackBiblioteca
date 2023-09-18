package edu.Biblioteca.Biblioteca1.servicios;


import edu.Biblioteca.Biblioteca1.entidades.*;
import edu.Biblioteca.Biblioteca1.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AdminServicio {

    @Autowired
    AdminRepositorio adminRepositorio;

    public List<Admin> getAll() {
        return adminRepositorio.findAll();
    }

    public Admin getById(Long id) {
        return adminRepositorio.findById(id).orElse(null);
    }

    public void save(Admin admin) {
        adminRepositorio.save(admin);
    }

    public void delete(Long id) {
        adminRepositorio.deleteById(id);
    }
}
