package io.github.rodrigojfagundes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.model.entity.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {
	
	
}
