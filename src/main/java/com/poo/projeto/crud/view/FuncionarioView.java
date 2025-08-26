package com.poo.projeto.crud.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FuncionarioView {

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtCPF = new JTextField(20);
    private final JTextField txtMatricula = new JTextField(20);
    private final JTextField txtCargo = new JTextField(20);
    private final JTextField txtSalario = new JTextField(10);

    private final JButton btnSalvar = new JButton("Salvar");
    private final JButton btnAtualizar = new JButton("Atualizar");
    private final JButton btnDeletar = new JButton("Deletar");
    private final JButton btnLimpar = new JButton("Limpar");

    private final JTable tabelaFuncionarios = new JTable();
    private final DefaultTableModel tableModel;

    public FuncionarioView() {
        super("Gerenciamento de Funcionários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        String[] colunas = {"ID", "Nome", "CPF", "Matrícula", "Cargo", "Salário"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaFuncionarios.setModel(tableModel);

    }


}
