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

    return this.http.post<Cliente>(`${this.apiURL}`, cliente);
}


//criando um metodo de nome ATUALIZAR q recebe um CLIENTE do tipo CLIENTE 
//com ID q vamos passar para o JAVA no BACKEND para EDITAR
//
atualizar( cliente: Cliente ) : Observable<any>{

    return this.http.put<Cliente>(`${this.apiURL}/${cliente.id}` , cliente);
}


//criano um metodo de nome GET CLIENTES, para PEDIR para o JAVA+SPRING(BACKEND)
//os CLIENTES q estao CAD no BANCO
//
getClientes() : Observable<Cliente[]>{

    return this.http.get<Cliente[]>(this.apiURL);
}

//metodo para OBTER um CLIENTE PELO O ID... 
//
getClienteById(id: number) : Observable<Cliente>{
    return this.http.get<any>(`${this.apiURL}/${id}`);
}


//metodo para DELETAR um CLIENTE PELO O ID... 
//esse metodo recebe um CLIENTE do tipo CLIENTE... Q e o cliente q queremos
//DELETAR (e passar para o JAVA+SPRING BACKEND DELETAR)
//
deletar(cliente: Cliente) : Observable<any>{

    return this.http.delete<any>(`${this.apiURL}/${cliente.id}`);
}
}
