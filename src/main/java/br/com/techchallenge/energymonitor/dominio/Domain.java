package br.com.techchallenge.energymonitor.dominio;

import java.io.Serializable;

import br.com.techchallenge.energymonitor.dto.Dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Domain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract Dto toDto();
}
