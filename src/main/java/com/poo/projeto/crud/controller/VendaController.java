package com.poo.projeto.crud.controller;

import com.poo.projeto.crud.model.Funcionario;
import com.poo.projeto.crud.model.ItemVenda;
import com.poo.projeto.crud.model.Produto;
import com.poo.projeto.crud.model.Venda;
import com.poo.projeto.crud.service.FuncionarioService;
import com.poo.projeto.crud.service.ProdutoService;
import com.poo.projeto.crud.service.VendaService;
import com.poo.projeto.crud.view.VendaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaController {

    private final VendaView view;
    private final VendaService vendaService;
    private final FuncionarioService funcionarioService;
    private final ProdutoService produtoService;

    private List<Funcionario> funcionarios;
    private List<Produto> produtos;
    private List<ItemVenda> itensDaVendaAtual = new ArrayList<>();


    public VendaController(VendaView view, VendaService vendaService, FuncionarioService funcionarioService, ProdutoService produtoService) {
        this.view = view;
        this.vendaService = vendaService;
        this.funcionarioService = funcionarioService;
        this.produtoService = produtoService;

        this.view.addSalvarVendaListener(e -> salvarVenda());
        this.view.addAdicionarItemListener(e -> adicionarItem());
        this.view.addListarVendasListener(e -> listarVendas());
        this.view.addDeletarVendaListener(e -> deletarVenda());

        carregarDadosIniciais();
        atualizarTotal();
    }

    private void carregarDadosIniciais() {
        try {
            funcionarios = funcionarioService.listarFuncionarios();
            produtos = produtoService.listarProdutos();

            for (Funcionario f : funcionarios) {
                view.getComboFuncionario().addItem(f.getNome());
            }
            for (Produto p : produtos) {
                view.getComboProduto().addItem(p.getNome());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarItem() {
        try {
            int selectedProductIndex = view.getComboProduto().getSelectedIndex();
            if (selectedProductIndex == -1) {
                JOptionPane.showMessageDialog(view, "Selecione um produto.");
                return;
            }

            Produto produtoSelecionado = produtos.get(selectedProductIndex);
            int quantidade = Integer.parseInt(view.getTxtQuantidade().getText());

            ItemVenda item = new ItemVenda();
            item.setProduto(produtoSelecionado);
            item.setQuantidade(quantidade);
            item.setPrecoUnitario(produtoSelecionado.getPreco());

            itensDaVendaAtual.add(item);

            double subtotal = item.getPrecoUnitario() * item.getQuantidade();
            view.getItensTableModel().addRow(new Object[]{
                    produtoSelecionado.getCodigo(),
                    produtoSelecionado.getNome(),
                    item.getPrecoUnitario(),
                    item.getQuantidade(),
                    subtotal
            });

            atualizarTotal();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(view, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao adicionar item: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarVenda() {
        try {
            if (itensDaVendaAtual.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Adicione pelo menos um item ao carrinho.");
                return;
            }

            Venda novaVenda = new Venda();
            ItemVenda item = new ItemVenda();

            int selectedFuncIndex = view.getComboFuncionario().getSelectedIndex();
            Funcionario funcionarioSelecionado = funcionarios.get(selectedFuncIndex);
            novaVenda.setFuncionario(funcionarioSelecionado);

            novaVenda.setItens(itensDaVendaAtual);


            vendaService.cadastrarVenda(novaVenda);


            JOptionPane.showMessageDialog(view, "Venda salva com sucesso!");

            resetarFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar a venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarVendas() {
        try {
            List<Venda> vendas = vendaService.ListarVendas();

            JDialog dialog = new JDialog(view, "Lista de Vendas Realizadas", true);
            dialog.setSize(600, 400);
            dialog.setLocationRelativeTo(view);


            String[] colunas = {"ID Venda", "Funcionário", "Data", "Total"};
            DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
            JTable tabelaVendas = new JTable(tableModel);


            for (Venda venda : vendas) {
                Object[] rowData = {
                        venda.getCodigo(),
                        venda.getFuncionario().getNome(),
                        venda.getDataVenda(),
                        venda.getValorTotal()
                };
                tableModel.addRow(rowData);
            }


            dialog.add(new JScrollPane(tabelaVendas));

            dialog.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao listar vendas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarVenda() {
        String idStr = JOptionPane.showInputDialog(
                view,
                "Digite o ID da venda que deseja deletar:",
                "Deletar Venda",
                JOptionPane.QUESTION_MESSAGE
        );

        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                Long id = Long.parseLong(idStr);
                vendaService.deletarVenda(id);

                JOptionPane.showMessageDialog(view, "Venda deletada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "ID inválido. Por favor, digite apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Erro ao deletar a venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetarFormulario() {
        itensDaVendaAtual.clear();
        view.getItensTableModel().setRowCount(0);
        view.getTxtQuantidade().setText("");
        view.getComboProduto().setSelectedIndex(0);
        view.getComboFuncionario().setSelectedIndex(0);
        atualizarTotal();
    }

    private void atualizarTotal() {
        double total = 0;
        for (ItemVenda item : itensDaVendaAtual) {
            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        view.getLblTotal().setText(String.format("Total: R$ %.2f", total));
    }




}