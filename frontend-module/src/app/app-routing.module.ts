import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProdutoComponent } from './produto/produto.component';
import { IncidenteComponent } from './incidente/incidente.component';
import { ProblemaComponent } from './problema/problema.component';
import { RiscoAcidenteComponent } from './risco-acidente/risco-acidente.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'produto', component: ProdutoComponent },
  { path: 'incidente', component: IncidenteComponent },
  { path: 'problema', component: ProblemaComponent },
  { path: 'riscoacidente', component: RiscoAcidenteComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }