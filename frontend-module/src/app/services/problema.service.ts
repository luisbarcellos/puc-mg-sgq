import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Problema } from '../models/problema';
import { Incidente } from '../models/incidente';

@Injectable({
  providedIn: 'root'
})
export class ProblemaService {
  url = 'http://localhost:8080/incidente-problema-service/v1/problemas';

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  // Remove um incidente do problema
  deleteIncidente(problema: Problema, incidente: Incidente) {
    return this.httpClient.delete<Incidente>(this.url + '/' + problema.idProblema + '/incidente/' + incidente.idIncidente, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Obtem todos os problemas
  getProblemas(): Observable<Problema[]> {
    return this.httpClient.get<Problema[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem um problema pelo id
  getProblemaById(id: number): Observable<Problema> {
    return this.httpClient.get<Problema>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva um problema
  saveProblema(problema: Problema): Observable<Problema> {
    return this.httpClient.post<Problema>(this.url, JSON.stringify(problema), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // atualiza um problema
  updateProblema(problema: Problema): Observable<Problema> {
    return this.httpClient.put<Problema>(this.url + '/' + problema.idProblema, JSON.stringify(problema), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // atualiza um incidente adicionando um produto
  updateIncidente(idProblema: number, idIncidente: number): Observable<Problema> {
    return this.httpClient.patch<Problema>(this.url + '/' + idProblema + '/incidente/' + idIncidente, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta um problema
  deleteProblema(idProblema: number) {
    return this.httpClient.delete<Problema>(this.url + '/' + idProblema, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}
