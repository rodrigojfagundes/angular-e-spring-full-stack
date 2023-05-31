import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './login/usuario';
import { environment } from '../environments/environment';

import { JwtConfig, JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

apiURL: string = environment.apiUrlBase + "/api/usuarios"
tokenURL: string = environment.apiUrlBase + environment.obterTokenUrl;
clientID: string = environment.clientId;
clientSecret: string = environment.clientSecret;
jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(
  private http: HttpClient
  ) { }

    obterToken(){
    const tokenString = localStorage.getItem('access_token')
    if(tokenString){
        const token = JSON.parse(tokenString).access_token
        return token;
    }
    return null;
}

isAuthenticated() : boolean {
    const token = this.obterToken();
    if (token) {
    const expired = this.jwtHelper.isTokenExpired(token);
    return !expired;
    }
    return false;
}



//criando o METODO SALVAR q RECEBE um USUARIO do tipo USUARIO
//
//vamos criar um METODO q vai servir para PEGAR um USUARIO q ta SENDO CAD no
// FRONT END (USERNAME + SENHA) e passar ele para o USUARIOCONTROLLER.JAVA 
//q roda no BACKEND (JAVA+SPRING)
salvar(usuario: Usuario) : Observable<any> {

    return this.http.post<any>(this.apiURL, usuario);
    }


tentarLogar(username: string, password: string) : Observable<any>{
    const params = new HttpParams()
        .set('username', username)
        .set('password', password)
        .set('grant_type', 'password')

const headers = {
    'Authorization' : 'Basic ' + btoa(`${this.clientID}:${this.clientSecret}`),
    'Content-Type' : 'application/x-www-form-urlencoded'
}

    return this.http.post(this.tokenURL, params.toString(), { headers });
    
}

}