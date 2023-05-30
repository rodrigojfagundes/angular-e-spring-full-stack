//classe de SERVICOS de CLIENTE... ELA pega as solicitacoes
//e envia para o SPRING do JAVA(backend)
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from './clientes/cliente';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

//injetando no construtor o HTTPCLIENT para fazer requesicao RESTFUL
//dai vamos ter acesso aos metodos POST, GET, PUT, DELETE, etc...
  constructor(private http: HttpClient) {
  
   }

//criando um metodo de nome SALVAR/CADASTRAR q recebe um CLIENTE do tipo CLIENTE 
//q vamos passar para o JAVA no BACKEND para SALVAR
salvar( cliente: Cliente ) : Observable<Cliente>{

    return this.http.post<Cliente>('http://localhost:8080/api/clientes', cliente);
}

    //METODO getcliente q retorna algo do TIPO CLIENTE
  getCliente() : Cliente{
    let cliente : Cliente = new Cliente();
    cliente.nome = 'Fulano de tal';
    cliente.cpf = '88888888';
    return cliente;
  }

}
