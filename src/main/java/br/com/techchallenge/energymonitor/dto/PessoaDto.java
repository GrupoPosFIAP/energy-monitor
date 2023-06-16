package br.com.techchallenge.energymonitor.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class PessoaDto implements Dto {

    @NotBlank(message = "Nome não pode ser vazio")
    private final String nome;

    @NotNull(message = "Data de nascimento deve ser informada")
    @PastOrPresent(message = "Data de nascimento deve ser no passado")
    private final LocalDate dataNascimento;
    
    private final Genero genero;
    private final Parentesco parentesco;

    @JsonCreator
    public PessoaDto(
            String nome,
            @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy") LocalDate dataNascimento,
            @JsonFormat(shape = Shape.STRING) Genero genero,
            @JsonFormat(shape = Shape.STRING) Parentesco parentesco
    ) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.parentesco = parentesco;
    }

    @Override
    public Pessoa toDomain() {
        return new Pessoa(nome, dataNascimento, genero, parentesco);
    }
}
