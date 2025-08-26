package com.poo.projeto.crud.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class FuncionarioView extends JFrame{

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

        JPanel formPanel = new JPanel(new GridLayout(7, 4, 7, 7));
        formPanel.setBorder(BorderFactory.createTitledBorder("Dados do Funcionário"));
        formPanel.add(new JLabel("ID:"));
        txtId.setEditable(false);
        formPanel.add(txtId);
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(txtCPF);
        formPanel.add(new JLabel("Matrícula:"));
        formPanel.add(txtMatricula);
        formPanel.add(new JLabel("Cargo:"));
        formPanel.add(txtCargo);
        formPanel.add(new JLabel("Salário:"));
        formPanel.add(txtSalario);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnDeletar);
        buttonPanel.add(btnLimpar);

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(tabelaFuncionarios), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtCPF() { return txtCPF;}
    public JTextField getTxtMatricula() { return txtMatricula;}
    public JTextField getTxtCargo() { return txtCargo; }
    public JTextField getTxtSalario() { return txtSalario; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getTabelaFuncionarios() { return tabelaFuncionarios; }

    public void addSalvarListener(ActionListener listener) { btnSalvar.addActionListener(listener); }
    public void addAtualizarListener(ActionListener listener) { btnAtualizar.addActionListener(listener); }
    public void addDeletarListener(ActionListener listener) { btnDeletar.addActionListener(listener); }
    public void addLimparListener(ActionListener listener) { btnLimpar.addActionListener(listener); }

}
