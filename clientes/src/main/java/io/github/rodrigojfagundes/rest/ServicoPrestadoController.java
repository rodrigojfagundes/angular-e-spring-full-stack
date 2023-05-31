package io.github.rodrigojfagundes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.rodrigojfagundes.model.entity.Cliente;
import io.github.rodrigojfagundes.model.entity.ServicoPrestado;
import io.github.rodrigojfagundes.repository.ClienteRepository;
import io.github.rodrigojfagundes.repository.ServicoPrestadoRepository;
import io.github.rodrigojfagundes.rest.dto.ServicoPrestadoDTO;
import io.github.rodrigojfagundes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

//classe para fazer chamada dos RECURSOS REST dos OBJETOS do tipo
//SERVICOPRESTADO... Ou seja quando o JAVASCRIPT+ANGULAR q ta rodando no FRONT
//requisitar os SERVICOPRESTADO, ele o JS+ANGULAR vai chamar os metodos dessa
//classe aqui, a classe SERVICOPRESTADOCONTROLLER, e ESSA CLASSE chama
//a classe SERVICOPRESTADOREPOSITORY, para acessar os DADOS NO BANCO
//
//para dizer q essa classe é um CONTROLADOR REST, vamos por o
//@RESTCONTROLLER... e o @RequestMapping e para dizer qual a ROTA
//do recurso... ou seja (localhost:8080/api/servicos-prestado)
@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();

		Cliente cliente = 
				clienteRepository
				.findById(idCliente)
				.orElseThrow(() -> 
				new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Cliente inexistente."));
		
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData( data );
		servicoPrestado.setCliente(cliente);

		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return repository.save(servicoPrestado);
		
		
	}
	
	
	//		METODO PARA PESQUISAR(BUSCAR) SERVICOSPRESTADOS
	//
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			){

		return repository.findByNomeClienteAndMes("%" + nome + "%", mes);	
	}
}
