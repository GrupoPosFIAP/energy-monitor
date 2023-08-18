package br.com.techchallenge.energymonitor.dto;

import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EnderecoDto implements Dto {

    private final Long id;

    @JsonProperty
    @NotBlank(message = "Informe a rua")
    private final String rua;

    @JsonProperty
    @NotNull(message = "Informe o número da residência")
    @Positive(message = "Número da residência deve ser positivo")
    private final Integer numero;

    @NotBlank(message = "Informe o bairro")
    private final String bairro;

    @NotBlank(message = "Informe a cidade")
    private final String cidade;

    @JsonProperty
    @NotNull(message = "Informe o estado")
    private final Estado estado;

    @JsonCreator
    public EnderecoDto(Long id, String rua,
                       @JsonFormat(shape = Shape.STRING) Integer numero, String bairro, String cidade,
                       @JsonFormat(shape = Shape.STRING) Estado estado) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public Endereco toDomain() {
        return new Endereco(id, rua, numero, bairro, cidade, estado);
    }
}
