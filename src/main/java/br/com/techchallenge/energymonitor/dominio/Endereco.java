package br.com.techchallenge.energymonitor.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
@EqualsAndHashCode(callSuper = true)
public class Endereco extends Domain {

    @JsonProperty
    private String rua;

    @JsonProperty
    private int numero;

    @JsonProperty
    private String bairro;

    @JsonProperty
    private String cidade;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Override
    public EnderecoDto toDto() {
        return new EnderecoDto(rua, numero, bairro, cidade, estado);
    }
}