import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from '../cliente';
import { ClientesService } from '../../clientes.service';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

//declarando as VAR/ATRIBUTOS
//
clientes: Cliente[] = [];
clienteSelecionado: Cliente;
mensagemSucesso: string;
mensagemErro: string;

  constructor(
  private service: ClientesService, 
  private router: Router) {}

  ngOnInit(): void {
    this.service
        .getClientes()
        .subscribe( resposta => this.clientes = resposta );
  }

    novoCadastro(){
    this.router.navigate(['/clientes/form'])
    }

preparaDelecao(cliente: Cliente){
this.clienteSelecionado = cliente;
}


//criando um METODO para DELETARCLIENTE... 
//ou seja recebe o CLIENTE q queremos deletar e vai ser passado para o 
//METODO DELETAR q ta no CLIENTES.SERVICES, e o DELETAR do CLIENTES.SERVICES
//passar para o JAVA+SPRING DELETAR
deletarCliente(){
this.service
.deletar(this.clienteSelecionado)
.subscribe( response =>  
{this.mensagemSucesso = 'Cliente deletado com sucesso!'
this.ngOnInit();
},
erro => this.mensagemErro = 'Ocorreu um erro ao deletar o cliente.'

)
}
}