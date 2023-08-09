package br.com.techchallenge.energymonitor.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eletronico")
@EqualsAndHashCode(callSuper = true)
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
