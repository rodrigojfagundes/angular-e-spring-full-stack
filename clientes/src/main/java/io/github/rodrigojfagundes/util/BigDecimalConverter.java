package io.github.rodrigojfagundes.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

//classe para converter a STRING VALOR(valor de um SERVICOPRESTADO) em BIGDECIMAL
@Component
public class BigDecimalConverter {

	//criando um metodo de nome CONVERTER do q retorna um BIGDECIMAL... e Esse
	//metodo RECEBE uma VAR STRING de nome VALUE
	//
	//EX: vai vim ASSIM 1.000,00 e tem q ficar assim 1000.00 
	public BigDecimal converter(String value) {
		//primeiro passo(REPLACEMENT) acessando o nosso valor de PRECO q vai tar
		//na VAR VALUE e REMOVENDO O (.) PONTO por nada
		//
		//e no segundo passo (REPLACEMENT) vamos transformar a VIRGULA(,) em PONTO
        value = value.replace(".", "").replace(",", ".");
        return new BigDecimal(value);
			
	}
}