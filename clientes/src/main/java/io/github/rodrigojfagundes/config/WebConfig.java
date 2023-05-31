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


//Criando a CLASSE WEBCONFIG q e uma CLASSE de CONFIGURACAO
@Configuration
public class WebConfig {

	
	//criando um metodo de nome CORSFILTERFILTERREGISTRATIONBEAN
	//do tipo BEAN (FILTERREGISTRATIONBEAN)
	//q vamos usar para REGISTRAR um FILTR de CORS
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
		//
		//EXEMPLO: informando q o LINK localhost:8080/clientes pd acessar TODOS (ALL) metodos
		//da CLASSE CLIENTECONTROLLER, mas poderia dizer q so acessar o POST e o GET
		//por exemplo...
		corsConfiguration.setAllowedMethods(all);
		corsConfiguration.setAllowCredentials(true);
	
		//criando um OBJ/VAR do tipo URLBASEDCORSCONFIGURATIONSOURCE de nome SOURCE
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//
		//com o metodo REGISTERCORSCONFIGURATION vamos informar para QUAIS URL a configuracao
		//de CORS acima funciona... no caso sera /** ou seja todas URL(/clientes...
		// /servicosprestados) etc...
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		//criando um OBJ/VAR de nome CORSFILTER do tipo CORSFILTER
		//q recebe a VAR/ OBJ SOURCE
		CorsFilter corsFilter = new CorsFilter(source);
		//
		//vamos registrar o SOURCE no BEAN... e passando q sera registrado o
		//CORSFILTER
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
		//
		//registrando o FILTRO q foi CRIADO no SPRING
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		
		return filter;
	}
	
}
