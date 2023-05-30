package io.github.rodrigojfagundes.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.rodrigojfagundes.model.entity.Cliente;
import io.github.rodrigojfagundes.repository.ClienteRepository;

//classe para fazer chamada dos RECURSOS REST dos OBJETOS do tipo
//CLIENTE... Ou seja quando o JAVASCRIPT+ANGULAR q ta rodando no FRONT
//requisitar os CLIENTES, ele o JS+ANGULAR vai chamar os metodos dessa
//classe aqui, a classe CLIENTECONTROLLER, e ESSA CLASSE chama
//a classe CLIENTEREPOSITORY, para acessar os DADOS NO BANCO
//
//para dizer q essa classe é um CONTROLADOR REST, vamos por o
//@RESTCONTROLLER... e o @RequestMapping e para dizer qual a ROTA
//do recurso... ou seja (localhost:8080/api/clientes)
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private final ClienteRepository repository;
	
	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	
	//criando um metodo para SALVAR um CLIENTE no BANCO
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {

		return repository.save(cliente);
	}
	
	
	//Metodo para pegar as informacoes PELO O ID do CLIENTE
	@GetMapping("{id}")
	public Cliente acharPorId( @PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
	
	//metodo para DELETAR um CLIENTE
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) { 
	repository
		.findById(id)
		.map( cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
	
	
	//criando metodo para ATUALIZAR um CLIENTE
	//
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar( @PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) { 
	repository
		.findById(id)
		.map( cliente -> {
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setCpf(clienteAtualizado.getCpf());			

			return repository.save(cliente);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
}