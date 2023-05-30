import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//importando modulo para fazer requisicoes rest
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component'
import { ClientesModule } from './clientes/clientes.module';
import { ClientesService } from './clientes.service'
//importando o MODULO de SERVICOPRESTADO q CRIAMOS
import { ServicoPrestadoModule } from './servico-prestado/servico-prestado.module'
//importando o SERVICO do tipo SERVICOPRESTADO q criamos
import { ServicoPrestadoService } from './servico-prestado.service'


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    TemplateModule,
    //importando o CLIENTEMODULE, para a aplicacao pd usar o MODULE
    //de CLIENTES
    ClientesModule,
    ServicoPrestadoModule
  ],
  //dentro do PROVIDERS fica as classes q podemos INJETAR
  providers: [
  ClientesService,
  ServicoPrestadoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
