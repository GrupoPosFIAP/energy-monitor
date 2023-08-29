package br.com.techchallenge.energymonitor.repository.eletronico;

import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EletronicoRepository extends JpaRepository<Eletronico, Long> {
}
