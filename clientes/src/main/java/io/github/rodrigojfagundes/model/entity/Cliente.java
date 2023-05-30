package io.github.rodrigojfagundes.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//ANNOTATION COLLUMN para nao permitir dados NULL, 	e no max 150 caracteres
	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false, length =11)
	private String cpf;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	
	

	
}
