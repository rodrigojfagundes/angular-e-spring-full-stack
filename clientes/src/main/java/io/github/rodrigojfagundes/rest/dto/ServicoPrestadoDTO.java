package io.github.rodrigojfagundes.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private String preco;
	@NotEmpty(message = "{campo.data.obrigatorio}")
	private String data;
	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
	
}
