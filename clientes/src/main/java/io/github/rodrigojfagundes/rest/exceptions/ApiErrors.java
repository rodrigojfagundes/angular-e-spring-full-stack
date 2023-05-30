package io.github.rodrigojfagundes.rest.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

//criar um objeto PADRONIZADO q vai representar o RETORNO quando der
//EXCEPTION na API, para dar um ERRO em JSON personalizado
//
public class ApiErrors {
	
	@Getter
	private List<String> errors;
	
	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiErrors(String message) {
		this.errors = Arrays.asList(message);
	}
}