package io.github.rodrigojfagundes.rest;

import java.util.List;

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
	
	
	//declarando uma VAR do tipo CLIENTEREPOSITORY de nome REPOSITORY
	private final ClienteRepository repository;
	
	//criando um construtor q vai RECEBER por INJECAO DE DEPEDENCIAS (@AUTOWIRED)
	//um REPOSITORY do tipo CLIENTEREPOSITORY, e o valor q vier nesse REPOSITORY
	//so vamos passar para a nossa VAR REPOSITORY(this.repository)
	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	
	//criando o metodo para PEGAR TODOS OS CLIENTES q ESTAO CAD no BANCO
	//e passar para o FRONT
	//metodo de nome OBTERTODOS, q vai retornar uma LISTA DE CLIENTES
	@GetMapping
	public List<Cliente> obterTodos(){
		return repository.findAll();
	}
	
	
	
	//criando um metodo para SALVAR um CLIENTE no BANCO
	//esse metodo vai receber do FRONT um CLIENTE em JSON com os seus DADOS
	//NOME, CPF, etc... e vai CAD no BANCO
	//
	// Colocando a ANNOTATION @POSTMAPPING pq no no PADRAO REST
	// quando nos vamos INSERIR um NOVO RECURSO nos usemos o metodo POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	//usando a ANNOTATION @REQUESTBODY para informar q o CLIENTE é o OBJ
	//q vai VIM da REQUISICAO do FRONT... Ou seja q e o CLIENTE q vai vim do FRONT
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		//e vamos passar o CLIENTE q recebemos do front para o REPOSITORY do tipo
		//CLIENTEREPOSITORY para SALVAR no BANCO
		return repository.save(cliente);
	}
	
	
	//Metodo para pegar as informacoes PELO O ID do CLIENTE
	//
	//como no RESTFUL para nos pegarmos uma informacao temos q usar o metodo GET
	//entao nos vamos usar a ANNOTATION @GETMAPPING, para (PEGAR/TRAZER)
	//o cliente conforme o ID
	//a ANNOTATION @PATHVARIABLE serve para dizer q o ID q recebemos no GETMAPPING e
	//o ID q sera passado para o ACHARPORID
	@GetMapping("{id}")
	public Cliente acharPorId( @PathVariable Integer id) {
		//
		//acessando o METODO FINDBYID do REPOSITORY do tipo CLIENTEREPOSITORY
		//e passando o ID para ele... Dai o CLIENTEREPOSITORY q herda metodos do JPA
		//ele vai pd acessar o BANCO etc...
		return repository
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
	
	//metodo para DELETAR um CLIENTE
	//para isso nos vamos usar a ANNOTATION @DELETEMAPPING, q ira receber o ID
	//do CLIENTE q queremos deletar
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//a ANNOTATION @PATHVARIABLE serve para dizer q o ID q recebemos no DELETEMAPPING e
	//o ID q sera passado para o DELETAR
	public void deletar(@PathVariable Integer id) {
		//acessando o METODO FINDBYID do REPOSITORY do tipo CLIENTEREPOSITORY
		//e passando o ID para ele... Dai vamos verificar SE o CLIENTE EXISTE
		//caso sim, nos vamos chamar o MAP e chamar o metodo DELETE do REPOSITORY
		//e pedir para deletar o CLIENTE... 
	repository
		.findById(id)
		.map( cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		})
		//se caso nao encontrr o cliente, dai chama a excecao a baixo
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
	
	
	//criando metodo para ATUALIZAR um CLIENTE
	//
	//a ANNOTATION @PATHVARIABLE serve para dizer q o ID vai ser um valor q vai vim
	//na URL da requisição (ou seja a ID do CLIENTE q queremos ATUALIZAR)
	//a ANNOTATION @REQUESTBODY vai receber um CLIENTE com os VALORES ATUALIZADOS
	//
	//como o metodo para ATULIZAR em RESTFUL e PUT, vamos ter q usar a ANNOTATION
	//@PUTMAPPING q recebe o ID do CLIENTE q queremos atualizar
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar( @PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
		//
		//acessando o METODO FINDBYID do REPOSITORY do tipo CLIENTEREPOSITORY
		//e passando o ID para ele... Dai vamos verificar SE o CLIENTE EXISTE
		//caso sim, nos vamos chamar o a funcao MAP 
	repository
		.findById(id)
		.map( cliente -> {
			//pegando os VALORES q tao no CLIENTEATUALIZADO (q sao os valores
			//q o usuario colocou na hora de atualizar)... E passando esses valores
			//para o CLIENTE
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setCpf(clienteAtualizado.getCpf());			
			//salvando o CLIENTE agora com os VALORES ATUALIZADOS
			return repository.save(cliente);
		})
		//se caso nao encontrr o cliente, dai chama a excecao a baixo
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
}