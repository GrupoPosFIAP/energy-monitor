package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import lombok.Data;

import java.time.LocalDate;
@Data
public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;
    private Parentesco parentesco;

}
