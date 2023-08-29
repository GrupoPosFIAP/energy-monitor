package br.com.techchallenge.energymonitor.dominio.eletronico;

public record EletronicoFilter(String nome, String modelo, int potencia) {

    public static Eletronico toEletronico(EletronicoFilter eletronicoFilter) {
        return new Eletronico(eletronicoFilter);
    }
}
