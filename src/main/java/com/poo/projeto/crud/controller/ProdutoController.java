package com.poo.projeto.crud.controller;

import com.poo.projeto.crud.model.Produto;
import com.poo.projeto.crud.service.ProdutoService;
import com.poo.projeto.crud.view.ProdutoView;

import java.util.List;

public class ProdutoController {

    private final ProdutoView view;
    private final ProdutoService produtoService;




    private void carregarTabela() {
        view.getTableModel().setRowCount(0);
        List<Produto> produtos = produtoService.listarProdutos();
        for (Produto p : produtos) {

            view.getTableModel().addRow(new Object[]{
                    p.getCodigo(), p.getNome(), p.getDescricao(), p.getPreco(), p.getEstoque()
            });
        }
    }
}






