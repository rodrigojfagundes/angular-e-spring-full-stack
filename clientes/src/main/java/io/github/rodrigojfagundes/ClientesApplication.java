package io.github.rodrigojfagundes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.rodrigojfagundes.model.entity.Cliente;
import io.github.rodrigojfagundes.repository.ClienteRepository;

@SpringBootApplication
public class ClientesApplication {

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			//instanciando um novo CLIENTE, e passando as informacoes
			Cliente cliente = Cliente.builder().cpf("00000000000").name("Fulano").build();
			//chamando o nosso repository(CLIENTEREPOSITORY) q foi injetado ali em cima
			//e pedindo para ele salvar o CLIENTE no BANCO... Pois a INTERFACE
			//CLIENTEREPOSITORY e responsavel por CAD no BANCO
			repository.save(cliente);
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}