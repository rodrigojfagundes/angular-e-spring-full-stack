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
	
	
	//declarando as var... @ID para dizer q vai ser o ID, @GENERATEDVALUE pois o
	//valor do ID e gerado automaticamente...
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//ANNOTATION @COLUMN para dizer q NAO PD SER NULL e o tamanho max da descricao
	@Column(nullable = false, length = 150)
	private String descricao;
	
	
	//ANNOTATION @MANYTOONE(muitos para um) ou seja sera uma associacao de MUITOS
	//SERVICOS para UM CLIENTE...
	//ANNOTATION JOINCOLLUMN e para dizer qual sera o NOME da COLUNA q tera a ASSOCIACAO
	//dos ID de SERVICOS e CLIENTE... Para sabermos qual o ID do CLIENTE contratou
	//quais SERVICOS
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column
	private BigDecimal valor;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
}
