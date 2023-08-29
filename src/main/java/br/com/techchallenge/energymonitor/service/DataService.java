package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.exception.EnergyMonitorException;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class DataService<D extends Domain> {

    private final BaseRepository<D> repository;


    public Dto get(Long id) {
        return repository.findById(id)
                .map(Domain::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Dto> getAll() {
        return repository.findAll().stream()
                .map(Domain::toDto)
                .collect(Collectors.toList());
    }

    public Page<Dto> getAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(Domain::toDto);
    }

    @SuppressWarnings("unchecked")
    public Dto save(Dto dto) {
        D entity = (D) dto.toDomain();
        var savedEntity = repository.save(entity);
        return savedEntity.toDto();
    }

    @SuppressWarnings("unchecked")
    public Dto update(Long id, Dto dto) {
        var foundEntity = repository.findById(id);

        if(!foundEntity.isPresent()) {
            throw new EnergyMonitorException("not found");
        }

        D entity = (D) dto.toDomain();

        entity.setId(id);

        return repository.save(entity).toDto();
    }

    public void delete(Long id) {
        var savedEntity = repository.findById(id)
                .orElseThrow(() -> new EnergyMonitorException("not found"));

        repository.delete(savedEntity);
    }
}
