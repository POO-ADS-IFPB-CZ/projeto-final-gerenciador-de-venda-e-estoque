package com.poo.projeto.crud.service;

import com.poo.projeto.crud.model.ItemVenda;
import com.poo.projeto.crud.model.Venda;
import com.poo.projeto.crud.model.Produto;
import com.poo.projeto.crud.repository.ProdutoRepository;
import com.poo.projeto.crud.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Venda> ListarVendas() {
        try {
            return vendaRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Erro ao listar as vendas.");
        }
    }

    public Venda buscarVendaPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Venda não encontrada!"));
    }

    @Transactional
    public Venda cadastrarVenda(Venda venda) {

        if(venda.getFuncionario() == null){
            throw new IllegalArgumentException("A venda precisa ter um funcionário associado.");
        }
        Double total = 0.0;
        for(ItemVenda item : venda.getItens()){
            item.setVenda(venda);

            Produto produto = produtoRepository.findById(item.getProduto().getCodigo())
                    .orElseThrow(() -> new RuntimeException("Produto não foi encontrado!"));

            if(produto.getEstoque() < item.getQuantidade()){
                throw new RuntimeException("Estoque insuficiente para: " + produto.getNome() + ", O estoque atual é de " + produto.getEstoque() + "itens.");
            }
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto);

            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        venda.setValorTotal(total);
        venda.setDataVenda(LocalDate.now());

        return vendaRepository.save(venda);
    }

    @Transactional
    public Venda atualizarVenda(Venda venda){
        Optional<Venda> optionalVenda = vendaRepository.findById(venda.getCodigo());
        if(optionalVenda.isPresent()){
            Venda vendaAtualizada = optionalVenda.get();
            vendaAtualizada.setItens(venda.getItens());
            return vendaRepository.save(venda);
        }
        else {
            throw new RuntimeException("Erro ao atualizar!");
        }
    }

    public void deletarVenda(Long id){
        if(!vendaRepository.existsById(id)){
            throw new RuntimeException("Venda não encontrada!");
        }
        vendaRepository.deleteById(id);
    }


}
