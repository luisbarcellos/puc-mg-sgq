import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ProdutoComponent } from './produto/produto.component';
import { HomeComponent } from './home/home.component';
import { IncidenteComponent } from './incidente/incidente.component';
import { ProblemaComponent } from './problema/problema.component';
import { RiscoAcidenteComponent } from './risco-acidente/risco-acidente.component';

@NgModule({
  declarations: [
    AppComponent,
    ProdutoComponent,
    HomeComponent,
    IncidenteComponent,
    ProblemaComponent,
    RiscoAcidenteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }