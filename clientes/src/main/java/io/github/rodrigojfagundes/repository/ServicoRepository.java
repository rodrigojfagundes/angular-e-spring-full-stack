package io.github.rodrigojfagundes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.model.entity.Servico;


public interface ServicoRepository extends JpaRepository<Servico, Integer> {
	
	
}
