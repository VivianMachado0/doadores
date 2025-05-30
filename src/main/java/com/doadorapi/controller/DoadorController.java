package com.doadorapi.controller;

import com.doadorapi.model.Doador;
import com.doadorapi.repository.DoadorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    private final DoadorRepository repository;
   // ✅ Injeção via construtor — melhor prática!
    public DoadorController(DoadorRepository repository) {
        this.repository = repository;
    }

    // ✅ CADASTRAR DOADOR COM VERIFICAÇÃO DE CPF
    @PostMapping
    public ResponseEntity<?> cadastrarDoador(@RequestBody Doador doador) {
        Optional<Doador> existente = repository.findByCpf(doador.getCpf());

        if (existente.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CPF já cadastrado.");
        }

        Doador salvo = repository.save(doador);
        System.out.println("Doador recebido: " + doador.getNome() + ", " + doador.getRua() + ", " + doador.getEmail());
        return ResponseEntity.ok(salvo);
    }

    // ✅ LISTAR TODOS OS DOADORES
    @GetMapping
    public List<Doador> listarDoadores() {
        return repository.findAll();
    }

    // ✅ BUSCAR DOADORES POR TIPO SANGUÍNEO E CIDADE
    @GetMapping("/buscar")
    public List<Doador> buscar(
            @RequestParam(required = false) String tipoSanguineo,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String bairro) {
        
        if (tipoSanguineo != null && cidade != null && bairro != null) {
            return repository.findByTipoSanguineoAndCidadeAndBairro(tipoSanguineo, cidade, bairro);
        } else if (tipoSanguineo != null && cidade != null) {
            return repository.findByTipoSanguineoAndCidade(tipoSanguineo, cidade);
        } else if (tipoSanguineo != null) {
            return repository.findByTipoSanguineo(tipoSanguineo);
        } else if (cidade != null) {
            return repository.findByCidade(cidade);
        } else if (bairro != null) {
            return repository.findByBairro(bairro);
        } else {
            return repository.findAll();
        }
    }
    
 // ✅ BUSCAR DOADOR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Doador> buscarPorId(@PathVariable Long id) {
        Optional<Doador> doador = repository.findById(id);
        return doador.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDoador(@PathVariable Long id, @RequestBody Doador doadorAtualizado) {
        return repository.findById(id)
            .map(doador -> {
                doador.setNome(doadorAtualizado.getNome());
                doador.setCpf(doadorAtualizado.getCpf());
                doador.setRua(doadorAtualizado.getRua());
                doador.setNumero(doadorAtualizado.getNumero());
                doador.setBairro(doadorAtualizado.getBairro());
                doador.setCidade(doadorAtualizado.getCidade());
                doador.setEstado(doadorAtualizado.getEstado());
                doador.setCep(doadorAtualizado.getCep());
                doador.setTelefone(doadorAtualizado.getTelefone());
                doador.setEmail(doadorAtualizado.getEmail());
                doador.setTipoSanguineo(doadorAtualizado.getTipoSanguineo());

                repository.save(doador);
                return ResponseEntity.ok(doador);
            })
            .orElse(ResponseEntity.notFound().build());
    }

}
