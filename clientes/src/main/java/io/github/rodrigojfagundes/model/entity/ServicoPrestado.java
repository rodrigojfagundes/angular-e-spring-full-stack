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

import lombok.Data;

//Criando a ENTIDADE/CLASSE SERVICOPRESTADO... Nela vai ficar armazenado as informacoes
//sobre cada servicoprestado
@Entity
@Data
public class ServicoPrestado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String descricao;
	
	
	//ANNOTATION @MANYTOONE(muitos para um) ou seja sera uma associacao de MUITOS
	//SERVICOSPRESTADOS para UM CLIENTE...
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column
	private BigDecimal valor;
	
	@Column
	private LocalDate data;
	
}
