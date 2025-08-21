package com.poo.projeto.crud.service;

import com.poo.projeto.crud.model.Venda;
import com.poo.projeto.crud.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    public List<Venda> ListarVendas() {
        try {
            return repository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Erro ao listar Vendas");
        }
    }

    public Venda buscarVendaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Venda não encontrada!"));
    }

    public Venda cadastrarVenda(Venda venda) {
        try{
            return repository.save(venda);
        }catch (Exception e){
            throw new RuntimeException("Erro ao Cadastrar a venda.");
        }

    }

    public void deletarVenda(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Venda não encontrada!");
        }
        repository.deleteById(id);
    }

    public Venda atualizarVenda(Venda venda){
        Optional<Venda> optionalVenda = repository.findById(venda.getCodigo());
        if(optionalVenda.isPresent()){
            Venda vendaAtualizada = optionalVenda.get();
            vendaAtualizada.setItens(venda.getItens());
            return repository.save(venda);
        }
        else {
            throw new RuntimeException("Erro ao Atualizar o produto!");
        }
    }




}
