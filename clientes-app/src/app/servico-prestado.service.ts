import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServicoPrestado } from './servico-prestado/servicoPrestado';
import { Observable } from 'rxjs';

import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicoPrestadoService {

apiURL: string = environment.apiUrlBase + "/api/servicos-prestados";

  constructor(private http: HttpClient) {}

 //criando um METODO SALVAR q vai receber um OBJ do tipo SERVICO-PRESTADO
  //e passar esse OBJ para a API q roda no BACKEND(JAVA+SPRING)
  //
  //e vamos retornar um OBSERVABLE do tipo SERVICOPRESTADO
  salvar(servicoPrestado: ServicoPrestado) : Observable<ServicoPrestado>{
 
    return this.http.post<ServicoPrestado>(this.apiURL, servicoPrestado);
  
  }
}
