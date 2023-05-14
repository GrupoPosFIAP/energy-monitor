package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import lombok.Data;

import java.time.LocalDate;
@Data
public class Pessoa implements BaseDomain{

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;
    private Parentesco parentesco;

    @Override
    public BaseDto toDto() {
        // FIXME Realizar o retorno do DTO após a criação do objeto
        return null;
    }
}
