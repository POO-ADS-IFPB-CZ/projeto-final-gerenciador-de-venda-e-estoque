package com.poo.projeto.crud.service;

import com.poo.projeto.crud.model.Venda;
import com.poo.projeto.crud.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.save(venda);
    }



}
