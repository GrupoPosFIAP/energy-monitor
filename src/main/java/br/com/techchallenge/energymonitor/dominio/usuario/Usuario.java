package br.com.techchallenge.energymonitor.dominio.usuario;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    @CPF(message = "CPF inválido.")
    private String cpf;
    @Column(unique = true)
    @Email(message = "Email inválido.")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Pessoa> pessoas;

    public Usuario(UsuarioBasico usuarioBasico) {
        this.nomeCompleto = usuarioBasico.nomeCompleto();
        this.username = usuarioBasico.username();
        this.cpf = usuarioBasico.cpf();
        this.email = usuarioBasico.email();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public boolean removeEndereco(Endereco endereco) {
        return this.enderecos.remove(endereco);
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    public boolean removePessoa(Pessoa pessoa) {
        return this.pessoas.remove(pessoa);
    }

}
