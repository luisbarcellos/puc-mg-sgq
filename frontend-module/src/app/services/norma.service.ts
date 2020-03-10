import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Norma } from '../models/norma';

@Injectable({
  providedIn: 'root'
})
export class NormaService {
  url = 'http://localhost:8081/mock-service/v1/normas';

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  // Obtem todos as normas
  getNormas(): Observable<Norma[]> {
    return this.httpClient.get<Norma[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem uma norma pelo id
  getNormaById(id: number): Observable<Norma> {
    return this.httpClient.get<Norma>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva uma norma
  saveNorma(norma: Norma): Observable<Norma> {
    return this.httpClient.post<Norma>(this.url, JSON.stringify(norma), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // atualiza uma norma
  updateIncidente(norma: Norma): Observable<Norma> {
    return this.httpClient.put<Norma>(this.url + '/' + norma.idNorma, JSON.stringify(norma), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta uma norma
  deleteNorma(idNorma: number) {
    return this.httpClient.delete<Norma>(this.url + '/' + idNorma, this.httpOptions)
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