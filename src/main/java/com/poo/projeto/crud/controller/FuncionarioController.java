package com.poo.projeto.crud.controller;

import com.poo.projeto.crud.model.Funcionario;
import com.poo.projeto.crud.service.FuncionarioService;
import com.poo.projeto.crud.view.FuncionarioView;

import javax.swing.*;
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

    private void salvarFuncionario() {

        if (!view.getTxtId().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Para alterar um funcionário existente, use o botão 'Atualizar'.", "Ação Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(view.getTxtNome().getText());
            funcionario.setCargo(view.getTxtCargo().getText());
            funcionario.setCpf(view.getTxtCPF().getText());
            funcionario.setMatricula(view.getTxtMatricula().getText());
            funcionario.setSalario(Double.parseDouble(view.getTxtSalario().getText()));

            funcionarioService.cadastrarFuncionario(funcionario);

            carregarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(view, "Funcionário salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar funcionário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void atualizarFuncionario() {

        if (view.getTxtId().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Selecione um funcionário na tabela para atualizar.", "Ação Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Funcionario funcionario = new Funcionario();


            funcionario.setId(Long.parseLong(view.getTxtId().getText()));
            funcionario.setMatricula(view.getTxtMatricula().getText());


            funcionario.setNome(view.getTxtNome().getText());
            funcionario.setCpf(view.getTxtCPF().getText());
            funcionario.setCargo(view.getTxtCargo().getText());
            funcionario.setSalario(Double.parseDouble(view.getTxtSalario().getText()));


            funcionarioService.atualizarFuncionario(funcionario);

            carregarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(view, "Funcionário atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao atualizar funcionário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


}
