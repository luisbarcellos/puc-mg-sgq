import { Component, OnInit } from '@angular/core';
import { Incidente } from '../models/incidente';
import { IncidenteService } from '../services/incidente.service';
import { NgForm } from '@angular/forms';
import { Produto } from '../models/produto';

@Component({
  selector: 'app-incidente',
  templateUrl: './incidente.component.html',
  styleUrls: ['./incidente.component.css']
})
export class IncidenteComponent implements OnInit {
  incidente = {} as Incidente;
  incidenteUpdate = {} as Incidente;
  incidentes: Incidente[];
  produto = {} as Produto;
  produtos: Produto[]; 

  constructor(private incidenteService: IncidenteService) {}
  
  ngOnInit() {
    this.getIncidentes();
    this.getProdutos(this.incidente);
  }

  // openLg(content) {
  //   this.modalService.open(content, { size: 'lg' });
  // }
  
  getProdutos(incidente: Incidente) {
    this.incidenteUpdate = incidente;
    this.produtos = incidente.produtos;
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

  // Chama o serviço para obtém todos os incidentes
  getIncidentes() {
    this.incidenteService.getIncidentes().subscribe((incidentes: Incidente[]) => {
      this.incidentes = incidentes;
    });
  }

  // deleta um incidente
  deleteIncidente(incidente: Incidente) {
    this.incidenteService.deleteIncidente(incidente).subscribe(() => {
      this.getIncidentes();
    });
  }

  // copia o incidente para ser editado.
  editIncidente(incidente: Incidente) {
    this.incidente = { ...incidente };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
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