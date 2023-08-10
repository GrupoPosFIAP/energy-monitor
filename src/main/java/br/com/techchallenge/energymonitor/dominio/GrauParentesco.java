package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.Dto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "parentesco")
@EqualsAndHashCode(callSuper = true)
public class GrauParentesco extends Domain {

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "parente_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Pessoa parente;

    @Enumerated(EnumType.STRING)
    private Parentesco grau;

    public GrauParentesco(Pessoa pessoa, Pessoa parente, Parentesco grau) {
        this.pessoa = pessoa;
        this.parente = parente;
        this.grau = grau;
    }

    @Override
    public Dto toDto() {
        throw new UnsupportedOperationException("Unimplemented method 'toDto'");
    }
}
