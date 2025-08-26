package com.poo.projeto.crud.controller;

import com.poo.projeto.crud.service.FuncionarioService;
import com.poo.projeto.crud.view.FuncionarioView;

public class FuncionarioController {

    private final FuncionarioView view;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioView view, FuncionarioService funcionarioService) {
        this.view = view;
        this.funcionarioService = funcionarioService;
    }



}
