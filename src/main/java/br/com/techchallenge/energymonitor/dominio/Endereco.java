package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "endereco")
@EqualsAndHashCode(callSuper = true)
public class Endereco extends Domain {

    private String rua;
    private int numero;
    private String bairro;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Endereco(Long id, String rua, int numero, String bairro, String cidade, Estado estado) {
        super(id);
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public EnderecoDto toDto() {
        return new EnderecoDto(getId(), rua, numero, bairro, cidade, estado);
    }
}