package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.consumo.Consumo;
import br.com.techchallenge.energymonitor.dto.ConsumoDTO;
import br.com.techchallenge.energymonitor.repository.consumo.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.techchallenge.energymonitor.repository.consumo.ConsumoUpdateRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ConsumoService extends DataService<Consumo> {

    @Autowired
    private ConsumoUpdateRepository consumoUpdateRepository;

    public ConsumoService(ConsumoRepository repository) {
        super(repository);
    }

    public Consumo updateConsumo(Long id) {
        return this.consumoUpdateRepository.updateConsumo(id, Instant.now());
    }
}
