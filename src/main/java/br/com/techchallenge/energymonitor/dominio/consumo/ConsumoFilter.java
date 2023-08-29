package br.com.techchallenge.energymonitor.dominio.consumo;

import java.time.Instant;

public record ConsumoFilter(Instant inicioFuncionamento, Instant fimFuncionamento, Double consumo) {
}
