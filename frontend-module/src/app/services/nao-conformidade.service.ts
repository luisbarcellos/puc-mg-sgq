import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { NaoConformidade } from '../models/naoconformidade';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NaoConformidadeService {
  url = 'http://localhost:8080/incidente-problema-service/v1/nao-conformidades';

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  // Remove um problema de uma não conformidade
  deleteProblema(idNaoConformidade: number, idProblema: number) {
    return this.httpClient.delete<NaoConformidade>(this.url + '/' + idNaoConformidade + '/problema/' + idProblema, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Remove um risco de acidente de uma não conformidade
  deleteRiscoAcidente(idNaoConformidade: number, idRiscoAcidente: number) {
    return this.httpClient.delete<NaoConformidade>(this.url + '/' + idNaoConformidade + '/risco-acidente/' + idRiscoAcidente, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Obtem todos as não conformidade
  getNaoConformidades(): Observable<NaoConformidade[]> {
    return this.httpClient.get<NaoConformidade[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem uma não conformidade pelo id
  getNaoConformidadeById(id: number): Observable<NaoConformidade> {
    return this.httpClient.get<NaoConformidade>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva uma não conformidade
  saveNaoConformidade(naoConformidade: NaoConformidade): Observable<NaoConformidade> {
    return this.httpClient.post<NaoConformidade>(this.url, JSON.stringify(naoConformidade), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // atualiza uma não conformidade
  updateNaoConformidade(naoConformidade: NaoConformidade): Observable<NaoConformidade> {
    return this.httpClient.put<NaoConformidade>(this.url + '/' + naoConformidade.idNaoConformidade, JSON.stringify(naoConformidade), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // atualiza uma não conformidade adicionando um problema
  updateProblema(idNaoConformidade: number, idProblema: number): Observable<NaoConformidade> {
    return this.httpClient.patch<NaoConformidade>(this.url + '/' + idNaoConformidade + '/problema/' + idProblema, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // atualiza uma não conformidade adicionando um risco de acidente
  updateRiscoAcidente(idNaoConformidade: number, idRiscoAcidente: number): Observable<NaoConformidade> {
    return this.httpClient.patch<NaoConformidade>(this.url + '/' + idNaoConformidade + '/risco-acidente/' + idRiscoAcidente, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta uma não conformidade
  deleteNaoConformidade(idNaoConformidade: number) {
    return this.httpClient.delete<NaoConformidade>(this.url + '/' + idNaoConformidade, this.httpOptions)
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
