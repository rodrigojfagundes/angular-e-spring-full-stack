package io.github.rodrigojfagundes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.model.entity.Cliente;

//criando a interface CLIENTE REPOSITORY, q irá fazer receber
//as solicitações da CLASSE CLIENTECONTROLLER... e em sequencia
//a CLIENTEREPOSITORY irá fazer a conexao ao BANCO para
//realizar a solicitacao
	//e essa classe HERDA de JPAREPOSITORY para se conectar
	//ao BANCO, o JPAREPOSITORY recebe um TIPO da ENTIDADE
	//no caso CLIENTE, e um ID, q vai ser no formato INTEGER
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
