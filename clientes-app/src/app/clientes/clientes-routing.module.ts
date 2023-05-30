import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientesFormComponent } from './clientes-form/clientes-form.component'

//aqui nos vamos definir a rota para as TELAS conforme os LINKS
//q estao na SIDEBAR 
const routes: Routes = [
{path: 'clientes-form' , component: ClientesFormComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
