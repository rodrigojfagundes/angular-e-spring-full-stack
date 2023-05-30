package io.github.rodrigojfagundes.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
		//criando uma VAR/OBJ de NOME CORSCONFIGURATION do TIPO CONFIGURATION
		//
		//criando uma LISTA de nome ALL q recebe um ASTERISTICO * ou seja
		//qd for chamado nos METODOS A BAIXO o ALL no caso * significa q ta 
		//liberado para todos... 
		List<String> all = Arrays.asList("*");
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		//
		//chamando o metodo SETALLOWDORIGINS do obj/var CORSCONFIGURATION
		//e informando quais os LINKS/DOMINIOS vao ter acesso a DETERMINADO METODO
		//de uma CLASSE (QUAIS DOMINIOS PD ACESSAR UMA API)
		//
		//EX: o localhost:8080/clientes pd acessar TODOS os METODOS (POST, GET, PUT, DELETE)
		//da classe CLIENTECONTROLLER, etc...
		//
		corsConfiguration.setAllowedOrigins(all);
		corsConfiguration.setAllowedHeaders(all);
		corsConfiguration.setAllowedMethods(all);
		corsConfiguration.setAllowCredentials(true);
	
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		CorsFilter corsFilter = new CorsFilter(source);

		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		
		return filter;
	}
	
}
