package com.poo.projeto.crud.service;

import com.poo.projeto.crud.model.Produto;
import com.poo.projeto.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Produto não encontrado!"));
    }

    public Produto salvarProduto(Produto produto) {
        try{
            return produtoRepository.save(produto);
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar o produto!");
        }
    }

    public Produto atualizarProduto(Produto produto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produto.getCodigo());
        if(optionalProduto.isPresent()){
            Produto produtoAtualizado = optionalProduto.get();
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setPreco(produto.getPreco());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setEstoque(produto.getEstoque());
            return produtoRepository.save(produtoAtualizado);
        }
        else {
            throw new RuntimeException("Erro ao Atualizar o produto!");
        }
    }

    public void deletarProduto(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado!");
        }
        produtoRepository.deleteById(id);
    }




}
