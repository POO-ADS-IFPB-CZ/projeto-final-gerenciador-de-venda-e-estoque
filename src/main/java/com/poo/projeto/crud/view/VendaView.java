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



    public VendaView() {
        super("Gerenciamento de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel vendaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        vendaPanel.setBorder(BorderFactory.createTitledBorder("Dados da Venda"));
        vendaPanel.add(new JLabel("Funcionário:"));
        vendaPanel.add(comboFuncionario);
        vendaPanel.add(btnSalvarVenda);


        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Item"));
        itemPanel.add(new JLabel("Produto:"));
        itemPanel.add(comboProduto);
        itemPanel.add(new JLabel("Quantidade:"));
        itemPanel.add(txtQuantidade);
        itemPanel.add(btnAdicionarItem);

        topPanel.add(vendaPanel);
        topPanel.add(itemPanel);
        add(topPanel, BorderLayout.NORTH);

        String[] colunasItens = {"ID Prod.", "Nome", "Preço Unitário", "Quantidade", "Subtotal"};
        itensTableModel = new DefaultTableModel(colunasItens, 0);
        tabelaItensVenda.setModel(itensTableModel);

        add(new JScrollPane(tabelaItensVenda), BorderLayout.CENTER);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(lblTotal);
        add(totalPanel, BorderLayout.SOUTH);


    }

    public JComboBox<String> getComboFuncionario() { return comboFuncionario; }
    public JComboBox<String> getComboProduto() { return comboProduto; }
    public JTextField getTxtQuantidade() { return txtQuantidade; }
    public DefaultTableModel getItensTableModel() { return itensTableModel; }
    public JLabel getLblTotal() { return lblTotal; }

    public void addSalvarVendaListener(ActionListener listener) { btnSalvarVenda.addActionListener(listener); }
    public void addAdicionarItemListener(ActionListener listener) { btnAdicionarItem.addActionListener(listener); }

}


