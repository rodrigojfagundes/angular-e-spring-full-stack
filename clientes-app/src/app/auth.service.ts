import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './login/usuario';
import { environment } from '../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

apiURL: string = environment.apiUrlBase + "/api/usuarios";

  constructor(
  private http: HttpClient
  ) { }

//criando o METODO SALVAR q RECEBE um USUARIO do tipo USUARIO
//
//vamos criar um METODO q vai servir para PEGAR um USUARIO q ta SENDO CAD no
// FRONT END (USERNAME + SENHA) e passar ele para o USUARIOCONTROLLER.JAVA 
//q roda no BACKEND (JAVA+SPRING)
salvar(usuario: Usuario) : Observable<any> {

    return this.http.post<any>(this.apiURL, usuario);

    }

}