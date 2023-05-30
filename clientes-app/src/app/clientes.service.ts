//classe de SERVICOS de CLIENTE... ELA pega as solicitacoes
//e envia para o JAVA(backend)
//
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from './clientes/cliente';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(private http: HttpClient) {}

//criando um metodo de nome SALVAR/CADASTRAR q recebe um CLIENTE do tipo CLIENTE 
//q vamos passar para o JAVA no BACKEND para SALVAR
//
salvar( cliente: Cliente ) : Observable<Cliente>{

    return this.http.post<Cliente>('http://localhost:8080/api/clientes', cliente);
}


//criano um metodo de nome GET CLIENTES, para PEDIR para o JAVA+SPRING(BACKEND)
//os CLIENTES q estao CAD no BANCO
//
getClientes() : Observable<Cliente[]>{
    return this.http.get<Cliente[]>('http://localhost:8080/api/clientes');
}

//metodo para OBTER um CLIENTE PELO O ID...
//
getClienteById(id: number) : Observable<Cliente>{
    return this.http.get<any>(`http://localhost:8080/api/clientes/${id}`);
}


}
