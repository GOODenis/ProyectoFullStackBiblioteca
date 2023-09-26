package edu.sistema.biblioteca.servicios;

import edu.sistema.biblioteca.entidades.*;
import edu.sistema.biblioteca.repositorios.*;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EstudianteServicio {

    @Autowired
    EstudianteRepositorio estudianteRepositorio;

    public List<Estudiante> getAll() {
        List<Estudiante> lista = new ArrayList<Estudiante>();
        estudianteRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Estudiante getById(Long id) {
        return estudianteRepositorio.findById(id).orElse(null);
    }

    public void save(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }

    public void delete(Long id) {
        estudianteRepositorio.deleteById(id);
    }
}
