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

}
