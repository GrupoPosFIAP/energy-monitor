package br.com.techchallenge.energymonitor.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class PessoaDto implements Dto {

    private final Long id;
    @NotBlank(message = "Nome não pode ser vazio")
    private final String nome;

    @NotNull(message = "Data de nascimento deve ser informada")
    @PastOrPresent(message = "Data de nascimento deve ser no passado")
    private final LocalDate dataNascimento;

    @NotNull
    private final Genero genero;

    @JsonCreator
    public PessoaDto(
            Long id,
            String nome,
            @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy") LocalDate dataNascimento,
            @JsonFormat(shape = Shape.STRING) Genero genero
    ) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    @Override
    public Pessoa toDomain() {
        return new Pessoa(id, nome, dataNascimento, genero);
    }
}
