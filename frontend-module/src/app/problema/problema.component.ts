import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { MdbTableDirective } from 'angular-bootstrap-md';
import { Problema } from '../models/problema';
import { Incidente } from '../models/incidente';
import { IncidenteService } from '../services/incidente.service';
import { NgForm } from '@angular/forms';
import { ProblemaService } from '../services/problema.service';

@Component({
  selector: 'app-problema',
  templateUrl: './problema.component.html',
  styleUrls: ['./problema.component.css']
})
export class ProblemaComponent implements OnInit {

  @ViewChild(MdbTableDirective, { static: true })
  mdbTable: MdbTableDirective;
  elements: any = [];
  searchText: string = '';
  previous: string;

  problema = {} as Problema;
  problemaUpdate = {} as Problema;
  problemas: Problema[];
  incidente = {} as Incidente;
  incidentes: Incidente[]

  constructor(private problemaService: ProblemaService, private incidenteService: IncidenteService) { }
  @HostListener('input') oninput() {
    this.pesquisarProblemas();
  }
  ngOnInit() {
    this.getProblemas();
  }

  // Chama o serviço para obtém todos os problemas
  getProblemas() {
    this.problemaService.getProblemas().subscribe((problemas: Problema[]) => {
      this.problemas = problemas;

      for (let i = 0; i < Object.keys(problemas).length; i++) {
        this.elements.push({
          idProblema: problemas[i].idProblema.toString(),
          tipo: problemas[i].tipo,
          gravidade: problemas[i].gravidade,
          descricao: problemas[i].descricao,
          dataInclusao: problemas[i].dataInclusao
        });
      }

      this.mdbTable.setDataSource(this.elements);
      this.previous = this.mdbTable.getDataSource();
    });
  }

  pesquisarProblemas() {
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

  getIncidentes(el: Problema) {
    this.problemaUpdate = this.problemas.find(inc => inc.idProblema == el.idProblema)
    this.incidentes = this.problemaUpdate.incidentes;
  }

  // busca todos incidentes para incluir em um problema
  getIncidentesAll(el: Problema) {
    this.problemaUpdate = this.problemas.find(inc => inc.idProblema == el.idProblema)
    this.incidenteService.getIncidentes().subscribe((incidentes: Incidente[]) => {
      this.incidentes = incidentes.filter(el => {
        return !this.problemaUpdate.incidentes.some(f => {
          return f.idIncidente === el.idIncidente;
        });
      });
    });
  }

  // Adiciona um incidente no problema
  updateIncidente(incidente: Incidente, problema: Problema) {
    this.problemaService.updateIncidente(problema.idProblema, incidente.idIncidente).subscribe(() => {

      this.problemaUpdate.incidentes.push(incidente);
      this.incidentes = this.incidentes.filter(prod => prod != incidente);
    });
  }

  // deleta um incidente
  deleteIncidente(incidente: Incidente) {
    this.problemaService.deleteIncidente(this.problemaUpdate, incidente).subscribe(() => {
      this.problemaUpdate.incidentes = this.problemaUpdate.incidentes.filter(prod => prod != incidente)
      this.incidentes = this.incidentes.filter(prod => prod != incidente);
    });
  }

  // defini se um problema será criado ou atualizado
  saveProblema(form: NgForm) {
    if (this.problema.idProblema !== undefined) {
      this.problemaService.updateProblema(this.problema).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.problemaService.saveProblema(this.problema).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  // deleta um problema
  deleteProblema(el: Problema) {
    console.log(el.idProblema);
    this.problemaService.deleteProblema(el.idProblema).subscribe(() => {
      this.elements = [];
      this.getProblemas();
    });
  }

  // copia o problema para ser editado.
  editProblema(el: Problema) {
    this.problema = { ...el };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.elements = [];
    this.getProblemas();
    form.resetForm();
    this.problema = {} as Problema;
  }

  // limpa o formulario
  cleanFormIncidente(form: NgForm) {
    form.resetForm();
    this.incidente = {} as Incidente;
  }
}