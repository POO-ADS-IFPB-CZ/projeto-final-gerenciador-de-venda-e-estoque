package com.poo.projeto.crud;


import com.poo.projeto.crud.controler.FuncionarioController;
import com.poo.projeto.crud.controler.ProdutoController;
import com.poo.projeto.crud.controler.VendaController;
import com.poo.projeto.crud.service.FuncionarioService;
import com.poo.projeto.crud.service.ProdutoService;
import com.poo.projeto.crud.service.VendaService;
import com.poo.projeto.crud.view.FuncionarioView;
import com.poo.projeto.crud.view.ProdutoView;
import com.poo.projeto.crud.view.VendaView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CrudApplication.class)
                .headless(false).run(args);

        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Bem vindo!");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(400, 300);
            mainFrame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JButton btnProdutos = new JButton("Cadastro de  Produtos");
            JButton btnFuncionarios = new JButton("Cadastro de Funcionários");
            JButton btnVendas = new JButton("Controle de Vendas");

            panel.add(btnProdutos);
            panel.add(btnFuncionarios);
            panel.add(btnVendas);

            btnProdutos.addActionListener(e -> {
                ProdutoService produtoService = context.getBean(ProdutoService.class);
                ProdutoView view = new ProdutoView();
                new ProdutoController(view, produtoService);
                view.setVisible(true);
            });

            btnFuncionarios.addActionListener(e -> {
                FuncionarioService funcionarioService = context.getBean(FuncionarioService.class);
                FuncionarioView view = new FuncionarioView();
                new FuncionarioController(view, funcionarioService);
                view.setVisible(true);
            });

            btnVendas.addActionListener(e -> {
                VendaService vendaService = context.getBean(VendaService.class);
                FuncionarioService funcionarioService = context.getBean(FuncionarioService.class);
                ProdutoService produtoService = context.getBean(ProdutoService.class);
                VendaView view = new VendaView();
                new VendaController(view, vendaService, funcionarioService, produtoService);
                view.setVisible(true);
            });

            mainFrame.add(panel);
            mainFrame.setVisible(true);
        });
    }
}




