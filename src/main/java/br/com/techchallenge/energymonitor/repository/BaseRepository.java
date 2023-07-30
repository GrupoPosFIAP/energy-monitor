package br.com.techchallenge.energymonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techchallenge.energymonitor.dominio.Domain;

public interface BaseRepository<T extends Domain> extends JpaRepository<T, Long> {
    

}
