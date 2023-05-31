package io.github.rodrigojfagundes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.rodrigojfagundes.model.entity.ServicoPrestado;

//criando a interface SERVICO REPOSITORY, q irá fazer receber
//as solicitações da CLASSE SERVICOSERVICE... e em sequencia
//a SERVICOREPOSITORY irá fazer a conexao ao BANCO para
//realizar a solicitacao
	//e essa classe HERDA de JPAREPOSITORY para se conectar
	//ao BANCO, o JPAREPOSITORY recebe um TIPO da ENTIDADE
	//no caso SERVICO, e um ID, q vai ser no formato INTEGER
	//
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

	//esse metodo RECEBE um NOME e um MES e RETORNA os SERVICOSPRESADOS para
	//esse NOME(CLIENTE) nesse DETERMINADO MES
	//
	//na ANNOTATION @QUERY nos passadmos como sera feita a CONSULTA SQL
	//
	//1 - select s from ServicoPrestado s
	//1- SELECIONANDO S(servicosprestados) FROM (apartir) de SERVICOSPRESTADO
	//1 - ou seja pegando o NOME do CLIENTE q PEDIU cada SERVICO
	//
	//2 - join s.cliente c where upper( c.nome ) like upper(:nome)
	//2 -  JOIN(JUNTANDO) os SERVICOSPRESTADOS(S) com o CLIENTE
	//2 - e DPS filtrando para trazer apenas os(SERVICOSPRESTADOS) em  q o NOME do 
	//2 - CLIENTE e IGUAL ao q foi passado como PARAMETRO
	//
	//3 - and MONTH (s.data) =:mes
	//3 - e verificando SE o MES desse SERVICOPRESTADO para esse CLIENTE
	//e o MESMO MES q foi PASSADO como PARAMETRO no METODO A BAIXO
	//RESUMINDO a QUERY A BAIXO, traz TODOS os SERVICOSPRESTADOS para o CLIENTE(NOME)
	//INFORMADO na DATA INFORMADA
	@Query(" select s from ServicoPrestado s join s.cliente c " +
	" where upper( c.nome ) like upper( :nome) and MONTH(s.data) =:mes ")
	List<ServicoPrestado> findByNomeClienteAndMes(
			@Param("nome") String nome, @Param("mes") Integer mes);
			
}
