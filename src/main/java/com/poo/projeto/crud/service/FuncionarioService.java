package com.poo.projeto.crud.service;

import com.poo.projeto.crud.model.Funcionario;
import com.poo.projeto.crud.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository repository;

    public List<Funcionario> listarFuncionarios() {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Erro ao listar funcionarios");
        }
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }




}