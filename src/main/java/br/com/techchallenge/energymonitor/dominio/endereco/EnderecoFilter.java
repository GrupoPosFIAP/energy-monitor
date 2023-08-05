package br.com.techchallenge.energymonitor.dominio.endereco;

import br.com.techchallenge.energymonitor.dominio.enums.Estado;

public record EnderecoFilter(String rua, String bairro, String cidade, Estado estado) {
}
