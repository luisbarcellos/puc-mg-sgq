import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { RiscoAcidente } from './models/riscoacidente';

@Injectable({
  providedIn: 'root'
})
export class RiscoAcidenteService {
  url = 'http://localhost:8080/incidente-problema-service/v1/risco-acidentes';

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  // Obtem todos os riscos de acidentes
  getRiscoAcidentes(): Observable<RiscoAcidente[]> {
    return this.httpClient.get<RiscoAcidente[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem um risco de acidente pelo id
  getRiscoAcidenteById(id: number): Observable<RiscoAcidente> {
    return this.httpClient.get<RiscoAcidente>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva um risco de acidente
  saveRiscoAcidente(riscoAcidente: RiscoAcidente): Observable<RiscoAcidente> {
    return this.httpClient.post<RiscoAcidente>(this.url, JSON.stringify(riscoAcidente), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // atualiza um risco de acidente
  updateRiscoAcidente(riscoAcidente: RiscoAcidente): Observable<RiscoAcidente> {
    return this.httpClient.put<RiscoAcidente>(this.url + '/' + riscoAcidente.idRiscoAcidente, JSON.stringify(riscoAcidente), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta um risco de acidente
  deleteRiscoAcidente(riscoAcidente: RiscoAcidente) {
    return this.httpClient.delete<RiscoAcidente>(this.url + '/' + riscoAcidente.idRiscoAcidente, this.httpOptions)
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