package br.com.techchallenge.energymonitor.dominio.eletronico;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dominio.consumo.Consumo;
import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_eletronico")
    private List<Consumo> consumos;

    public Eletronico(EletronicoFilter eletronicoFilter) {
        this.nome = eletronicoFilter.nome();
        this.modelo = eletronicoFilter.modelo();
        this.potencia = eletronicoFilter.potencia();
    }

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



    // CONSUMO
    public void addConsumo(Consumo consumo) {
        this.consumos.add(consumo);
    }

    public Consumo updateConsumo(int id, Consumo consumo) {
        return this.consumos.set(id, consumo);
    }
}
