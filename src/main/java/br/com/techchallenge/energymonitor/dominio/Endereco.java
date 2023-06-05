package br.com.techchallenge.energymonitor.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Endereco implements Domain {

    @JsonProperty
    private String rua;

    @JsonProperty
    private int numero;

    @JsonProperty
    private String bairro;

    @JsonProperty
    private String cidade;

    @JsonProperty
    private Estado estado;

    @Override
    public EnderecoDto toDto() {
        return new EnderecoDto(rua, numero, bairro, cidade, estado);
    }
}