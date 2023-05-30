package io.github.rodrigojfagundes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.model.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
