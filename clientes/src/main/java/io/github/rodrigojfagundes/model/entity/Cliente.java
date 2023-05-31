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
	
	//declarando os atributos/variaveis... q serao utilizados como COLUNAS no BANCO
	@Id
	//ANNOTATION GENERATEDVALUE e para GERAR os ID AUTOMATICAMENTE
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//ANNOTATION COLLUMN para nao permitir dados NULL, 	e no max 150 caracteres
	@Column(nullable = false, length = 150)
	//junto a ANNOTATION @NOTEMPTY estamos passando a mensagem
	//do CAMPO.NOME.OBRIGATORIO q ta no arquivo MESSAGES.PROPERTIES
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;

	@Column(nullable = false, length = 11)
	//junto a ANNOTATION @NOTNULL estamos passando a mensagem
	//do CAMPO.CPF.OBRIGATORIO q ta no arquivo MESSAGES.PROPERTIES
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message = "{campo.cpf.invalido}")
	private String cpf;
	
	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	
	//metodo para os CLIENTES terem uma DATA de CADASTRO padrao...
	//q no caso sera a data atual
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
}
