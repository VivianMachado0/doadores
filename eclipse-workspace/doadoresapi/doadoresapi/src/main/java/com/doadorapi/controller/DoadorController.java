package com.doadorapi.controller;

import com.doadorapi.model.Doador;
import com.doadorapi.repository.DoadorRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doadores")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DoadorController {

    private final DoadorRepository repository;

    public DoadorController(DoadorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarDoador(@RequestBody Doador doador) {
        Doador salvo = repository.save(doador);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<Doador> listarDoadores() {
        return repository.findAll();
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<Doador> buscarPorId(@PathVariable Long id) {
        Optional<Doador> doador = repository.findById(id);
        return doador.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDoador(@PathVariable Long id, @RequestBody Doador doadorAtualizado) {
        return repository.findById(id)
            .map(doadorExistente -> {
                doadorExistente.setNome(doadorAtualizado.getNome());
                doadorExistente.setRua(doadorAtualizado.getRua());
                doadorExistente.setNumero(doadorAtualizado.getNumero());
                doadorExistente.setBairro(doadorAtualizado.getBairro());
                doadorExistente.setCidade(doadorAtualizado.getCidade());
                doadorExistente.setTelefone(doadorAtualizado.getTelefone());
                doadorExistente.setTipoSanguineo(doadorAtualizado.getTipoSanguineo());
                repository.save(doadorExistente);
                return ResponseEntity.ok(doadorExistente);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirDoador(@PathVariable Long id) {
        return repository.findById(id)
            .map(doador -> {
                repository.delete(doador);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
