package br.com.techchallenge.energymonitor.dominio;

import java.time.LocalDate;
import java.util.Set;

import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "pessoas_enderecos",
        joinColumns = @JoinColumn(name = "pessoa_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    )
    private Set<Endereco> enderecos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pessoas_eletronicos", joinColumns = @JoinColumn(name = "pessoa_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "eletronico_id", referencedColumnName = "id"))
    private Set<Eletronico> eletronicos;

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
