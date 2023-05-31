package io.github.rodrigojfagundes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByUsername(String username);
	
}
