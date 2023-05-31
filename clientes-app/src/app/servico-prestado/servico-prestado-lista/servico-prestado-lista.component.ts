import { Component, OnInit } from '@angular/core';
import { ServicoPrestadoBusca } from './servicoPrestadoBusca';
import { ServicoPrestadoService } from '../../servico-prestado.service'

@Component({
  selector: 'app-servico-prestado-lista',
  templateUrl: './servico-prestado-lista.component.html',
  styleUrls: ['./servico-prestado-lista.component.css']
})
export class ServicoPrestadoListaComponent implements OnInit {

//declarando os ATRIBUTOS/VAR...
//vao ser util para nos PESQUISARMOS um SERVICO-PRESTADO...
//EX vamos colocar o NOME do CLIENTE e o MES... Dai vai aparecer os
//SERVICOPRESTADO para esse CLIENTE no MES EM ESPECIFICO
nome: string;
mes: number;
meses: number[];
lista: ServicoPrestadoBusca[];
message: string;

  constructor(
  private service: ServicoPrestadoService
  ) {
    this.meses = [1,2,3,4,5,6,7,8,9,10,11,12];
   }

  ngOnInit(): void {
  }

consultar(){
//chamando o OBJ/VAR SERVICE do tipo SERVICOPRESTADOSERVICE
//e passando para o METODO BUSCAR dele, um VALOR DE NOME E MES para BUSCAR
//os SERVICOPRESTADO, para um determinado cliente e um determinado mes
    this.service.buscar(this.nome, this.mes)
        .subscribe(response => {
        this.lista = response;
        if(this.lista.length <= 0) {
        this.message = "Nenhum Registro encontrado.";
        } else {
            this.message = null;
        }
        });
}
}
