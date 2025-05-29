package com.doadorapi.controller;

import com.doadorapi.model.Doador;
import com.doadorapi.repository.DoadorRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    private final DoadorRepository repository;

    public DoadorController(DoadorRepository repository) {
        this.repository = repository;
    }

    // LISTAR TODOS OS DOADORES
    @GetMapping
    public List<Doador> listarDoadores() {
        return repository.findAll();
    }

    // BUSCAR DOADORES POR TIPO SANGUÍNEO E CIDADE
    @GetMapping("/buscar")
    public List<Doador> buscarDoadores(@RequestParam String tipoSanguineo, @RequestParam String cidade) {
        return repository.findByTipoSanguineoAndCidade(tipoSanguineo, cidade);
    }

    // CADASTRAR DOADOR
    @PostMapping
    public Doador cadastrarDoador(@RequestBody Doador doador) {
        System.out.println("Doador recebido: " + doador.getNome() + ", " + doador.getRua() + ", " + doador.getEmail());
        return repository.save(doador);
    }

}
