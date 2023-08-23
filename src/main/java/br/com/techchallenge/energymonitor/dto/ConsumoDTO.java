package br.com.techchallenge.energymonitor.dto;

import br.com.techchallenge.energymonitor.dominio.consumo.Consumo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class ConsumoDTO implements Dto {

    private final Long id;

    @JsonProperty
    private final Instant inicioFuncionamento;

    @JsonProperty
    private final Instant fimFuncionamento;

    private final Double consumo;

    @JsonCreator
    public ConsumoDTO(Long id,
                      @JsonFormat(shape = JsonFormat.Shape.STRING) Instant inicioFuncionamento,
                      @JsonFormat(shape = JsonFormat.Shape.STRING) Instant fimFuncionamento,
                      @JsonFormat(shape = JsonFormat.Shape.STRING) Double consumo) {
        this.id = id;
        this.inicioFuncionamento = inicioFuncionamento;
        this.fimFuncionamento = fimFuncionamento;
        this.consumo = consumo;
    }

    @Override
    public Consumo toDomain() {
        return new Consumo(id, inicioFuncionamento, fimFuncionamento, consumo);
    }
}
