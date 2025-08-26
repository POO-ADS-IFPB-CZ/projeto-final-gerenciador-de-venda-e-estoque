package com.poo.projeto.crud.controller;

import com.poo.projeto.crud.model.Produto;
import com.poo.projeto.crud.service.ProdutoService;
import com.poo.projeto.crud.view.ProdutoView;

import javax.swing.*;
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

    private void salvarProduto() {
        if (!view.getTxtId().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Para alterar um produto existente, use o botão 'Atualizar'.", "Ação Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Produto produto = new Produto();
            produto.setNome(view.getTxtNome().getText());
            produto.setDescricao(view.getTxtDescricao().getText());
            produto.setPreco(Double.parseDouble(view.getTxtPreco().getText()));
            produto.setEstoque(Integer.parseInt(view.getTxtEstoque().getText()));

            produtoService.salvarProduto(produto);

            carregarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(view, "Produto salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarProduto() {
        if (view.getTxtId().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Selecione um produto na tabela para atualizar.", "Ação Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Produto produto = new Produto();
            produto.setCodigo(Long.parseLong(view.getTxtId().getText()));
            produto.setNome(view.getTxtNome().getText());
            produto.setDescricao(view.getTxtDescricao().getText());
            produto.setPreco(Double.parseDouble(view.getTxtPreco().getText()));
            produto.setEstoque(Integer.parseInt(view.getTxtEstoque().getText()));

            produtoService.atualizarProduto(produto);

            carregarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(view, "Produto atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao atualizar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarProduto() {
        try {
            int selectedRow = view.getTabelaProdutos().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Selecione um produto para deletar.");
                return;
            }
            Long id = (Long) view.getTableModel().getValueAt(selectedRow, 0);
            produtoService.deletarProduto(id);
            carregarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(view, "Produto deletado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao deletar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        view.getTxtId().setText("");
        view.getTxtNome().setText("");
        view.getTxtDescricao().setText("");
        view.getTxtPreco().setText("");
        view.getTxtEstoque().setText("");
    }

    private void preencherCamposComSelecao() {
        int selectedRow = view.getTabelaProdutos().getSelectedRow();
        if (selectedRow != -1) {
            view.getTxtId().setText(view.getTableModel().getValueAt(selectedRow, 0).toString());
            view.getTxtNome().setText(view.getTableModel().getValueAt(selectedRow, 1).toString());

            view.getTxtDescricao().setText(view.getTableModel().getValueAt(selectedRow, 2).toString());
            view.getTxtPreco().setText(view.getTableModel().getValueAt(selectedRow, 3).toString());
            view.getTxtEstoque().setText(view.getTableModel().getValueAt(selectedRow, 4).toString());
        }
    }

}






