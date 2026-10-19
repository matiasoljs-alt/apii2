package com.example.demo.controller;

import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;
    private ProdutoRepository repository;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<com.example.demo.model.Produto>> listar(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(service.listar(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.demo.model.Produto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<? extends Object> criar(@RequestBody ProdutoDTO dto) {
        try {
            Produto criado = service.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto){
//
//
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        boolean removeu = service.excluir(id);
        if (removeu) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();

    }
}