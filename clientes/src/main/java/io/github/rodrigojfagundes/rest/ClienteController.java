package io.github.rodrigojfagundes.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rodrigojfagundes.model.entity.Cliente;
import io.github.rodrigojfagundes.repository.ClienteRepository;

//classe para fazer chamada dos RECURSOS REST dos OBJETOS do tipo
//CLIENTE...
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private final ClienteRepository repository;
	
	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(Cliente cliente) {
		
		return repository.save(cliente);
	}
	
}