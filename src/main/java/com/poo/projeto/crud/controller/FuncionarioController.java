package com.poo.projeto.crud.controller;

import com.poo.projeto.crud.model.Funcionario;
import com.poo.projeto.crud.service.FuncionarioService;
import com.poo.projeto.crud.view.FuncionarioView;

import java.util.List;

public class FuncionarioController {

    private final FuncionarioView view;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioView view, FuncionarioService funcionarioService) {
        this.view = view;
        this.funcionarioService = funcionarioService;
    }

    private void carregarTabela() {
        view.getTableModel().setRowCount(0);
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        for (Funcionario f : funcionarios) {
            view.getTableModel().addRow(new Object[]{
                    f.getId(), f.getNome(), f.getCpf(), f.getMatricula(), f.getCargo(), f.getSalario()
            });
        }
    }


}
