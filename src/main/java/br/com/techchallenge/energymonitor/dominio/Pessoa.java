package br.com.techchallenge.energymonitor.dominio;

import java.time.LocalDate;

import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@Table(name = "pessoa")
@EqualsAndHashCode(callSuper = true)
public class Pessoa extends Domain {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;
    private Parentesco parentesco;

    public Pessoa(Long id, String nome, LocalDate dataNascimento, Genero genero, Parentesco parentesco) {
        super(id);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.parentesco = parentesco;
    }

    @Override
    public PessoaDto toDto() {
        return new PessoaDto(getId(), nome, dataNascimento, genero, parentesco);
    }
}
