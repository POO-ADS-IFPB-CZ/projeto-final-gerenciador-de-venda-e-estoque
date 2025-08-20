package com.poo.projeto.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "produto_codigo")
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Venda venda;

    private int quantidade;
    private Double precoUnitario;

}
