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
//ANNOTATION @REQUIREDARGSCONSTRUCTOR para criar CONSTRUTOR com ARGUMENTOS
@RequiredArgsConstructor
public class ServicoPrestadoController {
	
	
	//criando uma VAR/OBJ do tipo CLIENTEREPOSITORY de nome CLIENTEREPOSITORY
	//para conseguirmos acessar o BANCO
	private final ClienteRepository clienteRepository;
	//criando uma VAR/OBJ do tipo SERVICOPRESTADOREPOSITORY de nome 
	//REPOSITORY... Pois assim nos vamos conseguir acessar o BANCO
	private final ServicoPrestadoRepository repository;
	//injetando a CLASSE BIGDECIMALCONVERTEr
	private final BigDecimalConverter bigDecimalConverter;
	
	//criando um metodo de nome SALVAR q e do tipo SERVICOPRESTADO...
	//q vai receber os DADOS do DTO de tipo SERVICOPRESTADODTO
	//esse metodo usa a ANNOTATION @POSTMAPPING pois e para POSTAR/CADASTRAR/SALVAR
	@PostMapping
	//usando a ANNOTATION @REQUESTBODY para informar q o SERVICOPRESTADODTO é um OBJ
	//q vai VIM da REQUISICAO do FRONT... Ou seja q e o SERVICOPRESTADODTO
	//q vai vim do FRONT
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		//informando qual sera o PADRAO das DATAS, e colocando na VAR DATA
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		//pegando a ID do CLIENTE q foi passado pelo DTO do tipo SERVICOPRESTDODTO 
		//e armazenando na VAR do tipo INTEGER de nome IDCLIENTE
		//ou SEJA O CLIENTE Q SOLICITOU O SERVICO
		Integer idCliente = dto.getIdCliente();
		//criando uma VAR de nome CLIENTE do tipo CLIENTE e
		//chamando o METODO FINDBYID do OBJ/VAR CLIENTEREPOSITORY e passando para ele
		//a VAR IDCLIENTE q tem o ID do q veio do DTO (SERVICOPRESTADODTO)... 
		//Dai assim nos conseguimos pegar qual q e o CLIENTE q o DTO ta passando...
		//ou seja... QUAL E O CLIENTE Q SOLICITOU O SERVICO
		//SE NAO EXISTIR esse CLIENTE segundo o ID q veio pelo DTO... Dai (OR ELSE THROW)
		//retornamos uma mensagem de erro
		Cliente cliente = 
				clienteRepository
				.findById(idCliente)
				.orElseThrow(() -> 
				new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Cliente inexistente."));
		
		
		//instanciando um OBJ do tipo SERVICOPRESTADO de nome servicoprestado
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		//pegando os DADOS q vieram do OBJ/VAR DTO do tipo SERVICOPRESTADODTO (DTO)
		//e passando para a classe/obj/var SERVICOPRESTADO
		//
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData( data );
		servicoPrestado.setCliente(cliente);
		//chamando o nosso OBJ/VAR BIGDECIMALCONVERTER do tipo BIGDECIMALCONVERTER
		//e passando o PRECO q ta no DTO do tipo SERVICOPRESTADODTO e convertendo de
		//STRING para BIGDECIMAL... E add esse valor na VAR VALOR da CLASSE/ENTIDADE
		//SERVICOPRESTADO
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return repository.save(servicoPrestado);
		
		
	}
	
	
	//		METODO PARA PESQUISAR(BUSCAR) SERVICOSPRESTADOS
	//
	//metodo para fazer a PESQUISA de SERVICOSPRESTADO
	//pesquisar atraves do NOME DO CLIENTE, e ATRAVES DA DATA
	//
	//ANNOTATION @GETMAPPING pois vamos BUSCAR/PEGAR algo
	//metodo GET para o REST e para PEGAR/trazer no caso uma LISTA
	//de SERVICOSPRESTADO
	@GetMapping
	//ANNOTATION @REQUESTPARAM serve para dizer q estamos pegando uma INFORMACAO
	//q vem pela URL, tipo QUAL e o NOME do CLIENTE q vamos USAR para BUSCAR os
	//SERVICOSPRESTADOS a esse CLIENTE, etc...
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			){
		
		//chamando o REPOSITORY q é uma VAR/OBJ do tipo SERVICOPRESTADOREPOSITORY
		//e dps acessando o METODO FINDBYNOMECLIENTEANDMES, e para esse metodo
		//passando o NOME(do CLIENTE) e o MES(do servico)
		//dai esse metodo vai retornar para a gente os SERVICOS PRESTADOS para o
		//CLIENTE q foi PASSADO e no MES q foi INFORMADO
		return repository.findByNomeClienteAndMes("%" + nome + "%", mes);	
	}
}
