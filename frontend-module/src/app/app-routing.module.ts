import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProdutoComponent } from './produto/produto.component';
import { IncidenteComponent } from './incidente/incidente.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'produto', component: ProdutoComponent },
  { path: 'incidente', component: IncidenteComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }