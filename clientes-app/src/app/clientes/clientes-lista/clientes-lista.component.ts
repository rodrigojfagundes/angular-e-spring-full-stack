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

//criando o metodo NOVOCADASTRO, esse metodo sera chamado quando CLICAR
//no BOTAO de nome NOVO q tem na tela de CLIENTES
    novoCadastro(){

    this.router.navigate(['/clientes-form'])
    }


//criando um metodo para PERAPARDELETAR, q recebe o CLIENTE q queremos deletar
preparaDelecao(cliente: Cliente){

this.clienteSelecionado = cliente;
}


//criando um METODO para DELETARCLIENTE... 
//
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