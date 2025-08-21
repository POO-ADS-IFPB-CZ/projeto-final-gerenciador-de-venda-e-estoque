package com.poo.projeto.crud.service;

import com.poo.projeto.crud.model.Funcionario;
import com.poo.projeto.crud.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository repository;

    public List<Funcionario> listarFuncionarios() {
        return repository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
    }

    public Funcionario cadastrarFuncionario(Funcionario funcionario){
        try{
            return repository.save(funcionario);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("O Funcionário " + funcionario.getNome() + " - " + funcionario.getMatricula() + " já está cadastrado.");
        }
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario){
        Optional<Funcionario> optionalFuncionario = repository.findById(funcionario.getMatricula());
        if(optionalFuncionario.isPresent()){
            Funcionario funcionarioAtualizado = optionalFuncionario.get();
            funcionarioAtualizado.setNome(funcionario.getNome());
            funcionarioAtualizado.setCpf(funcionario.getCpf());
            funcionarioAtualizado.setCargo(funcionario.getCargo());
            funcionarioAtualizado.setSalario(funcionario.getSalario());
            return repository.save(funcionarioAtualizado);
        }else{
            throw new RuntimeException("Erro ao atualizar funcionário");
        }
    }

    public void deletarFuncionario(String cpf){
        if(!repository.existsFuncionarioByCpf(cpf)){
            throw new RuntimeException("Funcionaŕio não existe!");
        }
        repository.deleteByCpf(cpf);
    }








}