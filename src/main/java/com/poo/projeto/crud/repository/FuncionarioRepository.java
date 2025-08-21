package com.poo.projeto.crud.repository;

import com.poo.projeto.crud.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsFuncionarioByCpf(String cpf);

    void deleteByCpf(String cpf);
}
