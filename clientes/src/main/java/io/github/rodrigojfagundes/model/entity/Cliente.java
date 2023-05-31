package io.github.rodrigojfagundes.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//criando a classe/entidade CLIENTE, nela vai ficar armazenada as informações
//sobre os clientes... Tipo NOME, IDADE, CPF, etc...
//
//usando a ANNOTATION @ENTITY para mapear essa classe com o BANCO...
//e assim fazer uma TABELA no banco com o nome CLIENTE, e os ATRIBUTOS serem as
//COLUNAS
@Entity
//ANNOTATION @DATA ela cria os GET e SET automaticamente(invisivel) e 
//o TOSTRING, HASHCODE EQUALS e o CONSTRUTOR sem e COM PARAMETROS (de forma invisivel)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;

	@Column(nullable = false, length = 11)
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message = "{campo.cpf.invalido}")
	private String cpf;
	
	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
}
