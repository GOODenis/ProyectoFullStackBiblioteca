package edu.sistema.biblioteca.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.sistema.biblioteca.servicios.AdministradorServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AdministradorServicio administradorServicio;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(administradorServicio).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/registro", "/css/*", "/images/*",  "/images/*/*").permitAll()
				.requestMatchers("/libros", "/libros/*", "/estudiantes", "/estudiantes/*", "/prestamos", "/prestamos/*").hasRole("Adminitrador")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/logincheck")
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

}