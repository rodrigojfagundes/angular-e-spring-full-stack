//CLASSE SERVICO-PRESTADO-SERVICE... Ela vai servir para RECEBER
//o OBJ no tipo SERVICO-PRESTADO, e enviar ele para a API q fica
//no SERVICOPRESTADOCONTROLLER.JAVA q roda no BACKEND (JAVA+SPRING)
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

  salvar(servicoPrestado: ServicoPrestado) : Observable<ServicoPrestado>{
    return this.http.post<ServicoPrestado>(this.apiURL, servicoPrestado);
  
  }
}
