package com.doadorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doadorapi.model.Doador;

import java.util.List;

public interface DoadorRepository extends JpaRepository<Doador, Long> {

    // Consulta para filtrar por tipo sanguíneo e cidade
    List<Doador> findByTipoSanguineoAndCidade(String tipoSanguineo, String cidade);
}
