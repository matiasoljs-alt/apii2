package com.example.demo.repository;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    private final List<Produto> dados = new ArrayList<>();
    private Long proximoId = 1L;

    public ProdutoRepository() {
        dados.add(new Produto(proximoId++, "Teclado Mecânico", 250.0));
        dados.add(new Produto(proximoId++, "Mouse Gamer", 120.0));
        dados.add(new Produto(proximoId++, "Monitor 24 Pol", 890.0));
    }

    public List<Produto> findAll() {
        return new ArrayList<>(dados);
    }

    public Optional<Produto> findById(Long id) {
        return dados.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Produto save(Produto produto){
        if (produto.getId() == null){
            produto.setId(proximoId++);
            dados.add(produto);
        }else {
            deleteById(produto.getId());
            dados.add(produto);
        }

        return produto;
    }

    public  boolean deleteById(Long id) {
        return dados.removeIf(p -> p.getId().equals(id));
    }

}
