import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
//importando o nosso componente feito, o HELLOCOMPONENT
import { HelloComponent } from './hello/hello.component';
import { CursoAngularComponent } from './curso-angular/curso-angular.component';
import { TesteComponent } from './teste/teste.component'

@NgModule({
  declarations: [
    AppComponent,
    HelloComponent,
    CursoAngularComponent,
    TesteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
