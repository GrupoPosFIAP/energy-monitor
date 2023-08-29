package br.com.techchallenge.energymonitor.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EletronicoDto implements Dto {

    private final Long id;

    @JsonProperty
    @NotBlank(message = "Informe o nome do eletrônico")
    private final String nome;

    @JsonProperty
    @NotBlank(message = "Informe o modelo do eletrônico")
    private String modelo;

    @JsonProperty
    @NotBlank(message = "Informe a potência do eletrônico")
    @Positive(message = "A potência do eletrônico deve ser positiva")
    private int potencia;

    @JsonCreator
    public EletronicoDto(Long id, String nome, String modelo,
                         @JsonFormat (shape = JsonFormat.Shape.STRING) int potencia) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.potencia = potencia;
    }

    @Override
    public Eletronico toDomain() {
        return new Eletronico(id, nome, modelo, potencia);
    }
}
