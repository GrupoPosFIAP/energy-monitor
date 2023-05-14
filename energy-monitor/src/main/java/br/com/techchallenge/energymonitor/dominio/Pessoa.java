package br.com.techchallenge.energymonitor.dominio;

import java.time.LocalDate;

import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Pessoa implements Domain {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;
    private Parentesco parentesco;

    @Override
    public PessoaDto toDto() {
        return new PessoaDto(nome, dataNascimento, genero, parentesco);
    }

}
