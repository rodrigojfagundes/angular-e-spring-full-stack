import { Component, OnInit } from '@angular/core';
//importando o CLIENTE.TS
import { Cliente } from '../cliente';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

cliente: Cliente;
nome: String = 'Fulano';

  constructor() { 
    this.cliente = new Cliente();
    this.cliente.nome = 'Dougllas';
  }

  ngOnInit(): void {
  }

  clicar(){
    console.log('Cliquei!');
  }

}
