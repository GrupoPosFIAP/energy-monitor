package br.com.techchallenge.energymonitor.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.techchallenge.energymonitor.dominio.Endereco;
import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import lombok.Data;

@Data
public class EnderecoDto implements Dto {
	
    private final String rua;
    private final int numero;
    private final String bairro;
    private final String cidade;
    private final Estado estado;
    
    @JsonCreator
    public EnderecoDto(String rua, int numero, String bairro, String cidade, Estado estado) {
    	this.rua = rua;
    	this.numero = numero;
    	this.bairro = bairro;
    	this.cidade = cidade;
    	this.estado = estado;    	
    }
    
    @Override
    public Endereco toDomain() {
    	return new Endereco(rua, numero, bairro, cidade, estado);
    }    
}
