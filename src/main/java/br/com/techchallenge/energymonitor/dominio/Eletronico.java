package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Eletronico extends Domain{

    @JsonProperty
    private String nome;
    @JsonProperty
    private String modelo;
    @JsonProperty
    private int potencia;

    @Override
    public EletronicoDto toDto() {
        return new EletronicoDto(nome, modelo, potencia);
    }

}
