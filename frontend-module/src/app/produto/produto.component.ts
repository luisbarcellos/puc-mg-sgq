import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Produto } from '../models/produto';
import { ProdutoService } from '../services/produto.service';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {
  produto = {} as Produto;
  produtos: Produto[];

  constructor(private produtoService: ProdutoService) {}
  
  ngOnInit() {
    this.getProdutos();
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

  // Chama o serviço para obtém todos os produtos
  getProdutos() {
    this.produtoService.getProdutos().subscribe((produtos: Produto[]) => {
      this.produtos = produtos;
    });
  }

  // deleta um produto
  deleteProduto(produto: Produto) {
    this.produtoService.deleteProduto(produto).subscribe(() => {
      this.getProdutos();
    });
  }

  // copia o produto para ser editado.
  editProduto(produto: Produto) {
    this.produto = { ...produto };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.getProdutos();
    form.resetForm();
    this.produto = {} as Produto;
  }
}