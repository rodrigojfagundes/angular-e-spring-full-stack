package io.github.rodrigojfagundes.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//DTO é um OBJ q serve para FILTRAR transferencia de DADOS... Exemplo
//temos um OBJ do tipo SERVICOPRESTADO q tem DESCRICAO, CLIENTE, DATA, PRECO, etc... Mas
//queremos q seja transferido para o FRONT apenas o DESCRICAO e PRECO
//dai usemos o SERVICOPRESTADO_DTO... Vantagem é Controlar quais dados q vao ser
//jogados para o RESOURCER/controlador, e assim da mais seguranca e 
//economiza dados na REDE
//
@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

	private String descricao;
	private String preco;
	private String data;
	private Integer idCliente;
	
	
}
