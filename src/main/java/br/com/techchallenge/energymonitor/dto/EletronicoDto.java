package br.com.techchallenge.energymonitor.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techchallenge.energymonitor.dominio.Eletronico;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EletronicoDto implements Dto {

    @JsonProperty
    @NotBlank(message = "Informe o nome do eletrônico")
    private final String nome;

    @JsonProperty
    @NotBlank(message = "Informe o modelo do eletrônico")
    private String modelo;

    @JsonProperty
    @NotBlank(message = "Informe a potência do eletrônico")
    private int potencia;

    @JsonCreator
    public EletronicoDto(String nome, String modelo,
                         @JsonFormat (shape = JsonFormat.Shape.STRING) int potencia) {
        this.nome = nome;
        this.modelo = modelo;
        this.potencia = potencia;
    }

    @Override
    public Eletronico toDomain() {
        return new Eletronico(nome, modelo, potencia);
    }

}
