package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.exception.EnergyMonitorException;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DataService<D extends Domain> {

    private final BaseRepository<D> repository;

    public Dto get(long id) {
        return repository.findById(id)
                .map(Domain::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Dto save(Dto dto) {
        D entity = (D) dto.toDomain();
        var savedEntity = repository.save(entity);
        return savedEntity.toDto();
    }

    public Dto update(Dto dto) {
        D entity = (D) dto.toDomain();
        long id = entity.getId();

        repository.findById(id)
            .orElseThrow(() -> new EnergyMonitorException("not found"));

        return repository.save(entity).toDto();
    }

    public void delete(long id) {
        var savedEntity = repository.findById(id)
                .orElseThrow(() -> new EnergyMonitorException("not found"));

        repository.delete(savedEntity);
    }
}
