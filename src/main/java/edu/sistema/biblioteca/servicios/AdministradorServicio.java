package edu.sistema.biblioteca.servicios;

import edu.sistema.biblioteca.entidades.*;
import edu.sistema.biblioteca.repositorios.*;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

@Service
public class AdministradorServicio implements UserDetailsService{

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AdministradorRepositorio administradorRepositorioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private BCryptPasswordEncoder codificador; 


    public List<Administrador> getAll() {
        List<Administrador> lista = new ArrayList<Administrador>();
        administradorRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Administrador getById(Long id) {
        return administradorRepositorio.findById(id).orElse(null);
    }

    public void save(Administrador admin) {
        administradorRepositorio.save(admin);
    }

    public void delete(Long id) {
        administradorRepositorio.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrador administrador = administradorRepositorio.findByEmail(email);
        List<GrantedAuthority> ga = buildAuthorities(administrador.getRol());
        return buildUser(administrador, ga);
    }

    public User buildUser(Administrador administrador, List<GrantedAuthority> ga) {
        return new User(administrador.getEmail(), administrador.getPassword(), ga);
    }

    public List<GrantedAuthority> buildAuthorities(Rol rol) {
        List<GrantedAuthority> ga = new ArrayList<>();
        ga.add( new SimpleGrantedAuthority("ROLE_" + rol.getNombre()) );
        return ga;
    }

    @Transactional
    public void registro(Administrador administrador) {
        if (administradorRepositorio.existsByEmail(administrador.getEmail()))
            throw new IllegalArgumentException("Ya existe un usuario con este email");

        administrador.setPassword( codificador.encode(administrador.getPassword()) );
        administrador.setRol(rolRepositorio.findByNombre("Administrador").orElseThrow(() -> new IllegalArgumentException("Error al crear usuario")));
        administradorRepositorio.save(administrador);
    }

}
