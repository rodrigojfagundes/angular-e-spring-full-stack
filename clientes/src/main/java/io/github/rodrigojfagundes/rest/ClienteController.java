package io.github.rodrigojfagundes.rest;

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

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	
	private final ClienteRepository repository;
	
	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	
	//criando um metodo para SALVAR um CLIENTE no BANCO
	//esse metodo vai receber do FRONT um CLIENTE em JSON com os seus DADOS
	//NOME, CPF, etc... e vai CAD no BANCO
	//
	// Colocando a ANNOTATION @POSTMAPPING pq no no PADRAO REST
	// quando nos vamos INSERIR um NOVO RECURSO nos usemos o metodo POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {

		return repository.save(cliente);
	}
	
	
	//Metodo para pegar as informacoes PELO O ID do CLIENTE
	//
	@GetMapping("{id}")
	public Cliente acharPorId( @PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
	
	//metodo para DELETAR um CLIENTE
	//para isso nos vamos usar a ANNOTATION @DELETEMAPPING, q ira receber o ID
	//do CLIENTE q queremos deletar
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Integer id) { 
	repository
		.findById(id)
		.map( cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
	
	
	//criando metodo para ATUALIZAR um CLIENTE
	//
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar( @PathVariable Integer id, @RequestBody Cliente clienteAtualizado) { 
	repository
		.findById(id)
		.map( cliente -> {
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setCpf(clienteAtualizado.getCpf());			
			return repository.save(cliente);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
}