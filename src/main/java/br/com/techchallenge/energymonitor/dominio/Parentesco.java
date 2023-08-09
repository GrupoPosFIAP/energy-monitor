package br.com.techchallenge.energymonitor.dominio;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity
public class Parentesco {
    
    @EmbeddedId
    private ParentescoId id;

    @ManyToOne
    @MapsId("pessoa_id")
    @JoinColumn(name = "id")
    private Pessoa pessoa;

    @ManyToOne
    @MapsId("parente_id")
    @JoinColumn(name = "id")
    private Pessoa parente;

    private String grau;

    @Data
    @Embeddable
    public static class ParentescoId implements Serializable {

        @Column(name = "pessoa_id")
        private Long pessoaId;

        @Column(name = "parente_id")
        private Long parenteId;
    }
}
