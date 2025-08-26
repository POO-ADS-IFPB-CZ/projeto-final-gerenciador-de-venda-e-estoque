package com.poo.projeto.crud.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProdutoView extends JFrame {

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtDescricao = new JTextField(25);
    private final JTextField txtPreco = new JTextField(10);
    private final JTextField txtEstoque =new JTextField(10);

    private final JButton btnSalvar = new JButton("Salvar");
    private final JButton btnAtualizar = new JButton("Atualizar");
    private final JButton btnDeletar = new JButton("Deletar");
    private final JButton btnLimpar = new JButton("Limpar Campos");

    private final JTable tabelaProdutos = new JTable();
    private final DefaultTableModel tableModel;

    public ProdutoView() {
        super("Cadastro de Produtos");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] colunas = {"Código", "Nome", "Descrição", "Preço", "Estoque"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaProdutos.setModel(tableModel);
    }
}

