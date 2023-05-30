import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClientesService } from'../../clientes.service'

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

cliente: Cliente;
success: boolean = false;
errors: String[];

  constructor(private service: ClientesService) { 

this.cliente = new Cliente();
  }

  ngOnInit(): void {
  }


  //estamos passando um EVENTO q vem do FORMULARIO
  //CLIENTES-FORM.COMPONENT.HTML para ca
  onSubmit(){
    this.service.
    salvar(this.cliente)
    .subscribe( response => {
    this.success = true;
    })
  }
}
