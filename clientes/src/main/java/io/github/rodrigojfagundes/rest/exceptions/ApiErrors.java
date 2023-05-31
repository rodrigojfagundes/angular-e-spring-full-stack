package io.github.rodrigojfagundes.rest.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

//criar um objeto PADRONIZADO q vai representar o RETORNO quando der
//EXCEPTION na API, para dar um ERRO em JSON personalizado
//
public class ApiErrors {
	
	//declarando os ATRIBUTOS
	
	//criando uma LISTA de STRINGS de nome ERROS
	//
	//usando a ANNOTATION @GETTER para q o SPRING consiga capturar os ERROS
	//quando SPRING for transformar o OBJ APIERROS em JSON
	@Getter
	private List<String> errors;
	
	//criando um construtor, q recebe uma LISTA de ERROS como parametro
	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	
	//construtor para criarmos uma LISTA DE ERRORS a partir de um
	//VAR/OBJ MESSAGE, msg q vem da classe APPLICATIONCONTROLLERADVICE
	public ApiErrors(String message) {
		this.errors = Arrays.asList(message);
	}
}