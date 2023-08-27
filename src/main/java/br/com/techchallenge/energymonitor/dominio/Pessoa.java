package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.pessoa.PessoaDto;
import br.com.techchallenge.energymonitor.dto.pessoa.PessoaUsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pessoa")
@EqualsAndHashCode(callSuper = true)
public class Pessoa extends Domain {

    private String nome;
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pessoa(Long id, String nome, LocalDate dataNascimento, Genero genero, Parentesco parentesco) {
        super(id);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.parentesco = parentesco;
    }

    public Pessoa(PessoaUsuarioDTO pessoaDTO) {

        this.nome = pessoaDTO.nome();
        this.dataNascimento = pessoaDTO.dataNascimento();
        this.genero = pessoaDTO.genero();
        this.parentesco = pessoaDTO.parentesco();
    }

    @Override
    public PessoaDto toDto() {
        return new PessoaDto(getId(), nome, dataNascimento, genero, parentesco);
    }

}
