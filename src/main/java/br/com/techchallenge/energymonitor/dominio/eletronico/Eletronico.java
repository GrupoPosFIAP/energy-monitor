package br.com.techchallenge.energymonitor.dominio.eletronico;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Eletronico extends Domain {

    @JsonProperty
    private String nome;
    @JsonProperty
    private String modelo;
    @JsonProperty
    private int potencia;

    public Eletronico(Long id, String nome, String modelo, int potencia) {
        super(id);
        this.nome = nome;
        this.modelo = modelo;
        this.potencia = potencia;
    }

    @Override
    public EletronicoDto toDto() {
        return new EletronicoDto(getId(), nome, modelo, potencia);
    }
}
