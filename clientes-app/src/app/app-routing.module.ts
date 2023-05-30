import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';

//aqui nos amos definir as rotas... tipo QUAL PAGINA vai para
//qual COMPONENTE... Exemplo a baixo estamos fazendo a ROTA da pag
// (localhost/HOME)... Vamos dizer qual sera o COMPONENTE
//a ser ACESSADO quando cair no /HOME... (no caso vai cair no
//HOMECOMPONENT)
const routes: Routes = [
{ path : 'home', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
