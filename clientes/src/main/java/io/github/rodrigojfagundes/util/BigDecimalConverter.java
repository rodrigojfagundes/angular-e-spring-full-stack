package io.github.rodrigojfagundes.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class BigDecimalConverter {

 
	public BigDecimal converter(String value) {

        value = value.replace(".", "").replace(",", ".");
        return new BigDecimal(value);
			
	}
}