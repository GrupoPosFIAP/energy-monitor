package br.com.techchallenge.energymonitor.dominio;

import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.dto.pessoa.PessoaDto;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioBasicoDTO;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioDTO;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioEnderecoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private Set<Endereco> enderecos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Pessoa> pessoas;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private List<Eletronico> eletronicos;

    public Usuario(UsuarioBasicoDTO usuarioBasicoDTO) {
        this.nomeCompleto = usuarioBasicoDTO.nomeCompleto();
        this.username = usuarioBasicoDTO.username();
        this.cpf = usuarioBasicoDTO.cpf();
        this.email = usuarioBasicoDTO.email();
    }

    public Usuario(UsuarioEnderecoDTO usuarioEnderecoDTO) {

        Set<Endereco> enderecos = new HashSet<>();

        if(usuarioEnderecoDTO != null && !usuarioEnderecoDTO.enderecos().isEmpty()) {
            enderecos = usuarioEnderecoDTO.enderecos().stream().map(EnderecoDto::toDomain).collect(Collectors.toSet());
        }

        this.nomeCompleto = usuarioEnderecoDTO.nomeCompleto();
        this.username = usuarioEnderecoDTO.username();
        this.cpf = usuarioEnderecoDTO.cpf();
        this.email = usuarioEnderecoDTO.email();
        this.enderecos = enderecos;
    }

    public Usuario(UsuarioDTO usuarioDTO) {

        Set<Endereco> enderecos = new HashSet<>();
        Set<Pessoa> pessoas = new HashSet<>();

        if(usuarioDTO != null && !usuarioDTO.enderecos().isEmpty()) {
            enderecos = usuarioDTO.enderecos().stream().map(EnderecoDto::toDomain).collect(Collectors.toSet());
        }

        if(usuarioDTO != null && !usuarioDTO.pessoas().isEmpty()) {
            pessoas = usuarioDTO.pessoas().stream().map(PessoaDto::toDomain).collect(Collectors.toSet());
        }

        this.nomeCompleto = usuarioDTO.nomeCompleto();
        this.username = usuarioDTO.username();
        this.cpf = usuarioDTO.cpf();
        this.email = usuarioDTO.email();
        this.enderecos = enderecos;
        this.pessoas = pessoas;

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

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public boolean removeEndereco(Endereco endereco) {
        return this.enderecos.remove(endereco);
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    public boolean removePessoa(Pessoa pessoa) {
        return this.pessoas.remove(pessoa);
    }

    public void addEletronico(Eletronico eletronico) {
        this.eletronicos.add(eletronico);
    }

    public List<Eletronico> getEletronicos() {
        return eletronicos;
    }
}
