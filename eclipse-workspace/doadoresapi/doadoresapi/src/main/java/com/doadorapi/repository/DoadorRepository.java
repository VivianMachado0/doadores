package com.doadorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doadorapi.model.Doador;

import java.util.List;
import java.util.Optional;

public interface DoadorRepository extends JpaRepository<Doador, Long> {

    // Consulta para filtrar por tipo sanguíneo e cidade
    List<Doador> findByTipoSanguineoAndCidade(String tipoSanguineo, String cidade);
    Optional<Doador> findByCpf(String cpf);
    
    List<Doador> findByTipoSanguineo(String tipoSanguineo);

    List<Doador> findByCidade(String cidade);

    List<Doador> findByBairro(String bairro);


    List<Doador> findByTipoSanguineoAndCidadeAndBairro(String tipoSanguineo, String cidade, String bairro);
    
}
