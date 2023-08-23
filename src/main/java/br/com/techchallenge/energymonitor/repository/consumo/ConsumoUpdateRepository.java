package br.com.techchallenge.energymonitor.repository.consumo;

import br.com.techchallenge.energymonitor.dto.ConsumoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumoUpdateRepository extends JpaRepository<ConsumoDTO, Long> {

    @Query(value = " UPDATE consumo "                                                       +
                   " SET    fimFuncionamento = :fimFuncionamento "                          +
                   " ,      consumo          = (:fimFuncionamento - :inicioFuncionamento) " +
                   " WHERE id = :id" )
    ConsumoDTO updateConsumo(Long id, ConsumoDTO dto);

}
