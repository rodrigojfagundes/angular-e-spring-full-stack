import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router'
import { Cliente } from '../cliente';
import { ClientesService } from'../../clientes.service'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

cliente: Cliente;
success: boolean = false;
errors: String[];
id: number;

  constructor(
  private service: ClientesService ,
  private router: Router,
  private activatedRoute : ActivatedRoute
  ) { 
this.cliente = new Cliente();
  }

  ngOnInit(): void {

  let params : Observable<Params> = this.activatedRoute.params
  params.subscribe( urlParams => {
    this.id = urlParams['id'];
    if(this.id ){
    this.service
    .getClienteById(this.id)
    .subscribe( response => this.cliente = response ,
    errorResponse => this.cliente = new Cliente()
    )
    }
  })
}

voltarParaListagem(){

this.router.navigate(['/clientes-lista'])

}


  //ou seja estamos passando um EVENTO q vem do FORMULARIO
  //CLIENTES-FORM.COMPONENT.HTML para ca
  onSubmit(){

//nesse evento aqui... e se VIER COM UM ID, ou seja e para ATUALIZAR ENTAO
//vai executar o metodo de ATUALIZAR q ta no CLIENTE.SERVICES
if(this.id){

this.service
.atualizar(this.cliente)
.subscribe(response => {
    this.success = true;
    this.errors = null;
    //se houver erro ao tentar atualizar o cliente, nos vamos
    //add a mensagem ERRO AO ATUALIZAR O CLIENTE na LISTA ERRORS
}, errorResponse => {
    this.errors = ['Erro ao atualizar o cliente.']
})
}else{
//no caso esse EVENTO E QUANDO APERTAR NO BOTAO SALVAR do FRONT
  //ELE EXECUTAR O METODO DE SALVAR Q TA NO CLIENTE.SERVICES
    this.service.
    salvar(this.cliente)
    .subscribe( response => {
    this.success = true;
    this.errors = null;
    //com a linha a baixo, a pos cad vai mostrar a ID e a DATA de CADASTRO
    this.cliente = response;
    } , errorResponse => {
    this.success = false;
    this.errors = errorResponse.error.errors;
        }
    )
}
  
  }
}
