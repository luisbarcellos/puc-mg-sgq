import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { MdbTableDirective } from 'angular-bootstrap-md';
import { NaoConformidade } from '../models/naoconformidade';
import { Problema } from '../models/problema';
import { RiscoAcidente } from '../models/riscoacidente';
import { ProblemaService } from '../services/problema.service';
import { NaoConformidadeService } from '../services/nao-conformidade.service';
import { IncidenteService } from '../services/incidente.service';
import { RiscoAcidenteService } from '../services/risco-acidente.service';
import { NgForm } from '@angular/forms';
import { Norma } from '../models/norma';
import { NormaService } from '../services/norma.service';

@Component({
  selector: 'app-nao-conformidade',
  templateUrl: './nao-conformidade.component.html',
  styleUrls: ['./nao-conformidade.component.css']
})
export class NaoConformidadeComponent implements OnInit {
  @ViewChild(MdbTableDirective, { static: true })
  mdbTable: MdbTableDirective;
  elements: any = [];
  searchText: string = '';
  previous: string;

  naoConformidade = {} as NaoConformidade;
  naoConformidadeUpdate = {} as NaoConformidade;
  naoConformidades: NaoConformidade[];
  problema = {} as Problema;
  problemas: Problema[];
  riscoAcidente = {} as RiscoAcidente;
  riscoAcidentes: RiscoAcidente[];
  norma = {} as Norma;
  normas: Norma[];

  constructor(private naoConformidadeService: NaoConformidadeService, 
              private problemaService: ProblemaService, 
              private riscoAcidenteService: RiscoAcidenteService,
              private normaService: NormaService) { }
  @HostListener('input') oninput() {
    this.pesquisarNaoConformidades();
  }
  ngOnInit() {
    this.getNaoConformidades();
  }

  // Chama o serviço para obter todas os não conformidades
  getNaoConformidades() {
    this.naoConformidadeService.getNaoConformidades().subscribe((naoConformidades: NaoConformidade[]) => {
      this.naoConformidades = naoConformidades;

      for (let i = 0; i < Object.keys(naoConformidades).length; i++) {
        this.elements.push({
          idNaoConformidade: naoConformidades[i].idNaoConformidade.toString(),
          tipo: naoConformidades[i].tipo,
          descricao: naoConformidades[i].descricao,
          idProblema: naoConformidades[i]?.problema?.idProblema,
          idRiscoAcidente: naoConformidades[i]?.riscoAcidente?.idRiscoAcidente
        });
      }

      this.mdbTable.setDataSource(this.elements);
      this.previous = this.mdbTable.getDataSource();
    });
  }

  pesquisarNaoConformidades() {
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

  getRiscoAcidente(el: NaoConformidade) {
    this.naoConformidadeUpdate = this.naoConformidades.find(inc => inc.idNaoConformidade == el.idNaoConformidade)
    this.riscoAcidente = this.naoConformidadeUpdate.riscoAcidente;
  }

  getProblema(el: NaoConformidade) {
    this.naoConformidadeUpdate = this.naoConformidades.find(inc => inc.idNaoConformidade == el.idNaoConformidade)
    this.problema = this.naoConformidadeUpdate.problema;
  }

  // busca todos riscos de acidente para incluir em uma Nao conformidade
  getRiscoAcidentesAll(el: NaoConformidade) {
    this.naoConformidadeUpdate = this.naoConformidades.find(inc => inc.idNaoConformidade == el.idNaoConformidade);
    
    if (this.naoConformidadeUpdate.riscoAcidente == undefined) {
      this.riscoAcidenteService.getRiscoAcidentes().subscribe((riscoAcidentes: RiscoAcidente[]) => {
        
        //Calculando aqui
        this.riscoAcidentes = riscoAcidentes.filter(ra => {
          return !this.naoConformidades.some(nc => {
            return nc.riscoAcidente?.idRiscoAcidente === ra.idRiscoAcidente;
          })});

      });
    } else {
      this.riscoAcidentes = []
    }
  }

  // busca todas normas
  getNormas() {
    this.normaService.getNormas().subscribe((normas: Norma[]) => {
      console.log(normas);
      this.normas = normas;
    })
  }

  // busca todos problemas para incluir em uma Nao conformidade
  getProblemasAll(el: NaoConformidade) {
    this.naoConformidadeUpdate = this.naoConformidades.find(inc => inc.idNaoConformidade == el.idNaoConformidade);
    
    if (this.naoConformidadeUpdate.problema == undefined) {
      this.problemaService.getProblemas().subscribe((problemas: Problema[]) => {
        this.problemas = problemas.filter(p => {
          return !this.naoConformidades.some(nc => {
            return nc.problema?.idProblema === p.idProblema;
          })
        });
      });
    } else {
      this.problemas = []
    }
  }

  // Adiciona um risco de acidente na não conformidade
  updateRiscoAcidente(riscoAcidente: RiscoAcidente, naoConformidade: NaoConformidade) {
    this.naoConformidadeService.updateRiscoAcidente(naoConformidade.idNaoConformidade, riscoAcidente.idRiscoAcidente).subscribe(() => {
      this.naoConformidadeUpdate.riscoAcidente = riscoAcidente;
      this.riscoAcidentes = [];
      //Limpando a lista principal
      this.elements = []
      this.getNaoConformidades();
    });
  }

  // Adiciona um problema em uma não conformidade
  updateProblema(problema: Problema, naoConformidade: NaoConformidade) {
    this.naoConformidadeService.updateProblema(naoConformidade.idNaoConformidade, problema.idProblema).subscribe(() => {
      this.naoConformidadeUpdate.problema = problema;
      this.problemas = [];

      //Limpando a lista principal
      this.elements = []
      this.getNaoConformidades();
    });
  }

  // deleta um risco de acidente de uma não conformidade
  deleteRiscoAcidente(riscoAcidente: RiscoAcidente) {
    this.naoConformidadeService.deleteRiscoAcidente(this.naoConformidadeUpdate.idNaoConformidade, this.riscoAcidente.idRiscoAcidente).subscribe(() => {
      this.naoConformidadeUpdate.riscoAcidente = null;
      this.riscoAcidente = null;

      //Limpando a lista principal
      this.elements = []
      this.getNaoConformidades();
    });
  }

  // deleta um problema de uma não conformidade
  deleteProblema(problema: Problema) {
    this.naoConformidadeService.deleteProblema(this.naoConformidadeUpdate.idNaoConformidade, this.problema.idProblema).subscribe(() => {
      this.naoConformidadeUpdate.problema = null;
      this.problema = null;

      //Limpando a lista principal
      this.elements = []
      this.getNaoConformidades();
    });
  }

  // defini se uma não conformidade será criado ou atualizado
  saveNaoConformidade(form: NgForm) {
    if (this.naoConformidade.idNaoConformidade !== undefined) {
      this.naoConformidadeService.updateNaoConformidade(this.naoConformidade).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.naoConformidadeService.saveNaoConformidade(this.naoConformidade).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  // deleta uma não conformidade
  deleteNaoConformidade(el: NaoConformidade) {
    console.log(el.idNaoConformidade);
    this.naoConformidadeService.deleteNaoConformidade(el.idNaoConformidade).subscribe(() => {
      this.elements = [];
      this.getNaoConformidades();
    });
  }

  // copia a não conformidade para ser editada.
  editNaoConformidade(el: NaoConformidade) {
    this.naoConformidade = { ...el };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.elements = [];
    this.getNaoConformidades();
    form.resetForm();
    this.riscoAcidente = {} as RiscoAcidente;
    this.problema = {} as Problema;
    this.naoConformidade = {} as NaoConformidade;
  }
}
