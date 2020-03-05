import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Produto } from '../models/produto';
import { ProdutoService } from '../services/produto.service';
import { MdbTableDirective } from 'angular-bootstrap-md';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {
  @ViewChild(MdbTableDirective, { static: true }) 
  mdbTable: MdbTableDirective; 
  elements: any = []; 
  searchText: string = ''; 
  previous: string;
  
  produto = {} as Produto;
  produtos: Produto[];

  constructor(private produtoService: ProdutoService) {}
  @HostListener('input') oninput() {
    this.pesquisarProdutos();
  }
  
  ngOnInit() {
    this.getProdutos();
  }

  // Chama o serviço para obtém todos os produtos
  getProdutos() {
    this.produtoService.getProdutos().subscribe((produtos: Produto[]) => {
      this.produtos = produtos;

      for (let i = 0; i < Object.keys(produtos).length; i++) {
        this.elements.push({
            idProduto: produtos[i].idProduto.toString(), 
            nome: produtos[i].nome, 
            tipo: produtos[i].tipo, 
            descricao: produtos[i].descricao,
        });
      }

      this.mdbTable.setDataSource(this.elements);
      this.previous = this.mdbTable.getDataSource();
    });
  }

  pesquisarProdutos() {
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

  // defini se um produto será criado ou atualizado
  saveProduto(form: NgForm) {
    if (this.produto.idProduto !== undefined) {
      this.produtoService.updateProduto(this.produto).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.produtoService.saveProduto(this.produto).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  // deleta um produto
  deleteProduto(produto: Produto) {
    this.produtoService.deleteProduto(produto).subscribe(() => {
      this.elements = [];
      this.getProdutos();
    });
  }

  // copia o produto para ser editado.
  editProduto(produto: Produto) {
    this.produto = { ...produto };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.elements = [];
    this.getProdutos();
    form.resetForm();
    this.produto = {} as Produto;
  }
}