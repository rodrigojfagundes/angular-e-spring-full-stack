//classe de SERVICOS de CLIENTE... Ela pega as solicitacoes
//e envia para o SPRING do JAVA(backend)
//
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from './clientes/cliente';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

apiURL: string = environment.apiUrlBase + '/api/clientes';

  constructor(private http: HttpClient) {}

//criando um metodo de nome SALVAR/CADASTRAR q recebe um CLIENTE do tipo CLIENTE 
//q vamos passar para o JAVA no BACKEND para SALVAR
//
salvar( cliente: Cliente ) : Observable<Cliente>{
const tokenString = localStorage.getItem('access_token')
const token = JSON.parse(tokenString)

const headers = {
    'Authorization' : 'Bearer ' + token.access_token
}
  //chamando o METODO POST da VAR/OBJ HTTP do tipo HTTPCLIENT e passando
  //esse POST q vamos ENVIAR um OBJ/VAR do tipo CLIENTE
  //dai nos INFORMAMOS qual a URL q ta RODANDO A API no BACKEND
  //e INFORMAMOS TBM qual e a VAR/OBJ q sera CADASTRADO, no CASO CLIENTE
    return this.http.post<Cliente>(`${this.apiURL}`, cliente, {headers});
}


//criando um metodo de nome ATUALIZAR q recebe um CLIENTE do tipo CLIENTE 
//com ID q vamos passar para o JAVA no BACKEND para EDITAR
//
atualizar( cliente: Cliente ) : Observable<any>{
    return this.http.put<Cliente>(`${this.apiURL}/${cliente.id}` , cliente);
}


//criano um metodo de nome GET CLIENTES, para PEDIR para o JAVA+SPRING(BACKEND)
//os CLIENTES q estao CAD no BANCO
getClientes() : Observable<Cliente[]>{
const tokenString = localStorage.getItem('access_token')
const token = JSON.parse(tokenString)
const headers = {
    'Authorization' : 'Bearer ' + token.access_token
}
    return this.http.get<Cliente[]>(this.apiURL, {headers});
}

//metodo para OBTER um CLIENTE PELO O ID...
//
getClienteById(id: number) : Observable<Cliente>{

    return this.http.get<any>(`${this.apiURL}/${id}`);
}


//metodo para DELETAR um CLIENTE PELO O ID... 
//
deletar(cliente: Cliente) : Observable<any>{

    return this.http.delete<any>(`${this.apiURL}/${cliente.id}`);
}
}
