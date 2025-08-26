package com.poo.projeto.crud.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VendaView extends JFrame {


    private final JComboBox<String> comboFuncionario = new JComboBox<>();
    private final JButton btnSalvarVenda = new JButton("Salvar Venda");

    private final JComboBox<String> comboProduto = new JComboBox<>();
    private final JTextField txtQuantidade = new JTextField(5);
    private final JButton btnAdicionarItem = new JButton("Adicionar produto ao carrinho");

    private final JTable tabelaItensVenda = new JTable();
    private final DefaultTableModel itensTableModel;

    private final JLabel lblTotal = new JLabel("Total: R$ 0.00");



}
