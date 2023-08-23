package br.com.techchallenge.energymonitor.dominio.consumo;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dto.ConsumoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.function.UnaryOperator;

@Data
@Entity
@NoArgsConstructor
@Table(name = "consumo")
@EqualsAndHashCode(callSuper = true)
public class Consumo extends Domain  {

    @JsonProperty
    private Instant inicioFuncionamento;

    @JsonProperty
    private Instant fimFuncionamento;

    private Double consumo;

    public Consumo(Long id, Instant inicioFuncionamento, Instant fimFuncionamento, Double consumo) {
        super(id);
        this.inicioFuncionamento = inicioFuncionamento;
        this.fimFuncionamento = fimFuncionamento;
        this.consumo = consumo;
    }

    @Override
    public ConsumoDTO toDto() {
        return new ConsumoDTO(getId(), inicioFuncionamento, fimFuncionamento, consumo);
    }
}
