package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dominio.consumo.Consumo;
import br.com.techchallenge.energymonitor.dominio.consumo.ConsumoUpdater;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import br.com.techchallenge.energymonitor.repository.consumo.ConsumoCalculadoRepository;
import br.com.techchallenge.energymonitor.repository.consumo.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service("consumoDataService")
public class ConsumoDataService extends DataService<Consumo> {

    @Autowired
    ConsumoRepository repository;

    @Autowired
    ConsumoCalculadoRepository calculadoRepository;

    public ConsumoDataService(BaseRepository<Consumo> repository) {
        super(repository);
    }

    public List<Dto> calculadoRepository(Long id, ConsumoUpdater updated) {
        return calculadoRepository.atualizarConsumo(id, updated).stream().map(Domain::toDto).toList();
    }
}
