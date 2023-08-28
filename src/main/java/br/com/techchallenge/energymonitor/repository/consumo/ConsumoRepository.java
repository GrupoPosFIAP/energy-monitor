package br.com.techchallenge.energymonitor.repository.consumo;

import br.com.techchallenge.energymonitor.dominio.consumo.Consumo;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumoRepository extends BaseRepository<Consumo> {

}
