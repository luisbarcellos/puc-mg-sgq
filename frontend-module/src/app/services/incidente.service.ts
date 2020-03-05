import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Produto } from '../models/produto';
import { Incidente } from '../models/incidente';

@Injectable({
  providedIn: 'root'
})
export class IncidenteService {
  url = 'http://localhost:8080/incidente-problema-service/v1/incidentes';

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  // Remove um produto do incidente
  deleteProduto(incidente: Incidente, produto: Produto) {
    return this.httpClient.delete<Incidente>(this.url + '/' + incidente.idIncidente + '/produto/' + produto.idProduto, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Obtem todos os incidentes
  getIncidentes(): Observable<Incidente[]> {
    return this.httpClient.get<Incidente[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem um incidente pelo id
  getIncidenteById(id: number): Observable<Incidente> {
    return this.httpClient.get<Incidente>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva um incidente
  saveIncidente(incidente: Incidente): Observable<Incidente> {
    return this.httpClient.post<Incidente>(this.url, JSON.stringify(incidente), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // atualiza um incidente
  updateIncidente(incidente: Incidente): Observable<Incidente> {
    return this.httpClient.put<Incidente>(this.url + '/' + incidente.idIncidente, JSON.stringify(incidente), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta um incidente
  deleteIncidente(idIncidente: number) {
    return this.httpClient.delete<Incidente>(this.url + '/' + idIncidente, this.httpOptions)
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
