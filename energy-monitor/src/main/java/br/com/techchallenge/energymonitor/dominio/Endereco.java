package br.com.techchallenge.energymonitor.entities;

public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {
    }

    public Endereco(String rua, int numero, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Endereco endereco = (Endereco) object;
        return  numero == endereco.numero  &&
                java.util.Objects.equals(rua, endereco.rua)       &&
                java.util.Objects.equals(bairro, endereco.bairro) &&
                java.util.Objects.equals(cidade, endereco.cidade) &&
                java.util.Objects.equals(estado, endereco.estado);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), rua, numero, bairro, cidade, estado);
    }
}