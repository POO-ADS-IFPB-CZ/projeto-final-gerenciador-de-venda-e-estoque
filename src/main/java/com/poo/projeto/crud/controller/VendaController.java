package com.poo.projeto.crud.controler;

import com.poo.projeto.crud.model.Funcionario;
import com.poo.projeto.crud.model.ItemVenda;
import com.poo.projeto.crud.model.Produto;
import com.poo.projeto.crud.model.Venda;
import com.poo.projeto.crud.service.FuncionarioService;
import com.poo.projeto.crud.service.ProdutoService;
import com.poo.projeto.crud.service.VendaService;
import com.poo.projeto.crud.view.VendaView;

import javax.swing.*;
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

        carregarDadosIniciais();
        atualizarTotal();
    }



}