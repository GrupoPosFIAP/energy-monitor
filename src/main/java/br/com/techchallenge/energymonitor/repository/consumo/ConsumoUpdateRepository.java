package br.com.techchallenge.energymonitor.repository.consumo;

import br.com.techchallenge.energymonitor.dominio.consumo.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface ConsumoUpdateRepository extends JpaRepository<Consumo, Long> {

    @Query(value = "UPDATE consumo SET fimFuncionamento = :fimFuncionamento , consumo = (fimFuncionamento - inicioFuncionamento) WHERE id = :id", nativeQuery = true)
    Consumo updateConsumo(@Param("id") Long id, @Param("fimFuncionamento") Instant fimFuncionamento);

}
