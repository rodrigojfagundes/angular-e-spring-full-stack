package io.github.rodrigojfagundes.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//Criando a ENTIDADE/CLASSE SERVICO... Nela vai ficar armazenado as informacoes
//sobre cada servico
//
//colocando o ANNOTATION @ENTITY para criar uma TABELA de nome SERVICO no BANCO
//com as COLUNAS com o nome das VARIAVEIS
//o ANNOTATION @DATA e para fazer os CONSTRUTORES, GET e SET e HASHCODE EQUALS 
@Entity
@Data
public class ServicoPrestado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column
	private BigDecimal valor;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
}
