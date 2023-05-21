package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Endereco implements Domain {

    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private Estado estado;

    @Override
    public EnderecoDto toDto() {
        return new EnderecoDto(rua, numero, bairro, cidade, estado);
    }
}