package io.github.rodrigojfagundes.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

//criando a CLASSE USUARIO... Q vai receber os dados do REGISTRO/LOGIN do FRONT
//
@Data
@NoArgsConstructor
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, name="login")
	private String username;
	
	@Column(name = "senha")
	private String password;

}
