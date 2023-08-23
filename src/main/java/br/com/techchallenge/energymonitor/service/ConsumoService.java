package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.consumo.Consumo;
import br.com.techchallenge.energymonitor.repository.consumo.ConsumoRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("consumoRepository")
public class ConsumoService extends DataService<Consumo> {

    public ConsumoService(ConsumoRepository repository) {
        super(repository);
    }
}
