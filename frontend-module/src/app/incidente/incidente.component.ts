import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { Incidente } from '../models/incidente';
import { IncidenteService } from '../services/incidente.service';
import { NgForm } from '@angular/forms';
import { Produto } from '../models/produto';
import { MdbTableDirective } from 'angular-bootstrap-md';

@Component({
  selector: 'app-incidente',
  templateUrl: './incidente.component.html',
  styleUrls: ['./incidente.component.css']
})
export class IncidenteComponent implements OnInit {
  @ViewChild(MdbTableDirective, { static: true }) 
  mdbTable: MdbTableDirective; 
  elements: any = []; 
  searchText: string = ''; 
  previous: string;
            
  incidente = {} as Incidente;
  incidenteUpdate = {} as Incidente;
  incidentes: Incidente[];
  produto = {} as Produto;
  produtos: Produto[]

  constructor(private incidenteService: IncidenteService) {} 
  @HostListener('input') oninput() {
    this.pesquisarIncidentes();
  }
  ngOnInit() {
    this.getIncidentes();
  }
 
  // Chama o serviço para obtém todos os incidentes
  getIncidentes() {
    this.incidenteService.getIncidentes().subscribe((incidentes: Incidente[]) => {
      this.incidentes = incidentes;
      
      for (let i = 0; i < Object.keys(incidentes).length; i++) {
        this.elements.push({
            idIncidente: incidentes[i].idIncidente.toString(), 
            tipo: incidentes[i].tipo, 
            gravidade: incidentes[i].gravidade, 
            descricao: incidentes[i].descricao,
            dataInclusao: incidentes[i].dataInclusao
        });
      }

      this.mdbTable.setDataSource(this.elements);
      this.previous = this.mdbTable.getDataSource();
    });
  }
  
  pesquisarIncidentes() {
    const prev = this.mdbTable.getDataSource(); 
        if (!this.searchText) {
            this.mdbTable.setDataSource(this.previous); 
            this.elements = this.mdbTable.getDataSource();
        } if (this.searchText) {
          this.elements =
          this.mdbTable.searchLocalDataBy(this.searchText);
          this.mdbTable.setDataSource(prev);
        }
  }

  getProdutos(el: Incidente) {
    this.incidenteUpdate = this.incidentes.find(inc => inc.idIncidente == el.idIncidente)
    this.produtos = this.incidenteUpdate.produtos;
  }

  // deleta um produto
  deleteProduto(produto: Produto) {
    this.incidenteService.deleteProduto(this.incidenteUpdate, produto).subscribe(() => {
      this.incidenteUpdate.produtos = this.incidenteUpdate.produtos.filter(prod => prod != produto)
      this.produtos = this.produtos.filter(prod => prod != produto);
    });
  }

  // defini se um incidente será criado ou atualizado
  saveIncidente(form: NgForm) {
    if (this.incidente.idIncidente !== undefined) {
      this.incidenteService.updateIncidente(this.incidente).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.incidenteService.saveIncidente(this.incidente).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  // deleta um incidente
  deleteIncidente(el: Incidente) {
    console.log(el.idIncidente);
    this.incidenteService.deleteIncidente(el.idIncidente).subscribe(() => {
      this.elements = [];
      this.getIncidentes();
    });
  }

  // copia o incidente para ser editado.
  editIncidente(el: Incidente) {
    this.incidente = { ...el };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.elements = [];
    this.getIncidentes();
    form.resetForm();
    this.incidente = {} as Incidente;
  }

  // limpa o formulario
  cleanFormProduto(form: NgForm) {
    form.resetForm();
    this.produto = {} as Produto;
  }
}