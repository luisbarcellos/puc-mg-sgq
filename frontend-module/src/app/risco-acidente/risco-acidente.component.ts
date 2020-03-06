import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { MdbTableDirective } from 'angular-bootstrap-md';
import { RiscoAcidente } from '../models/riscoacidente';
import { RiscoAcidenteService } from '../risco-acidente.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-risco-acidente',
  templateUrl: './risco-acidente.component.html',
  styleUrls: ['./risco-acidente.component.css']
})
export class RiscoAcidenteComponent implements OnInit {
  @ViewChild(MdbTableDirective, { static: true }) 
  mdbTable: MdbTableDirective; 
  elements: any = []; 
  searchText: string = ''; 
  previous: string;
  
  riscoAcidente = {} as RiscoAcidente;
  riscoAcidentes: RiscoAcidente[];

  constructor(private riscoAcidenteService: RiscoAcidenteService) {}
  @HostListener('input') oninput() {
    this.pesquisarRiscoAcidentes();
  }
  
  ngOnInit() {
    this.getRiscoAcidentes();
  }

  // Chama o serviço para obtém todos os riscos de acidentes
  getRiscoAcidentes() {
    this.riscoAcidenteService.getRiscoAcidentes().subscribe((riscoAcidentes: RiscoAcidente[]) => {
      this.riscoAcidentes = riscoAcidentes;

      for (let i = 0; i < Object.keys(riscoAcidentes).length; i++) {
        this.elements.push({
            idRiscoAcidente: riscoAcidentes[i].idRiscoAcidente.toString(), 
            tipo: riscoAcidentes[i].tipo, 
            descricao: riscoAcidentes[i].descricao,
        });
      }

      this.mdbTable.setDataSource(this.elements);
      this.previous = this.mdbTable.getDataSource();
    });
  }

  pesquisarRiscoAcidentes() {
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

  // defini se um risco de acidente será criado ou atualizado
  saveRiscoAcidente(form: NgForm) {
    if (this.riscoAcidente.idRiscoAcidente !== undefined) {
      this.riscoAcidenteService.updateRiscoAcidente(this.riscoAcidente).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.riscoAcidenteService.saveRiscoAcidente(this.riscoAcidente).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  // deleta um risco de acidente
  deleteRiscoAcidente(riscoAcidente: RiscoAcidente) {
    this.riscoAcidenteService.deleteRiscoAcidente(riscoAcidente).subscribe(() => {
      this.elements = [];
      this.getRiscoAcidentes();
    });
  }

  // copia o risco de acidente para ser editado.
  editRiscoAcidente(riscoAcidente: RiscoAcidente) {
    this.riscoAcidente = { ...riscoAcidente };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.elements = [];
    this.getRiscoAcidentes();
    form.resetForm();
    this.riscoAcidente = {} as RiscoAcidente;
  }
}