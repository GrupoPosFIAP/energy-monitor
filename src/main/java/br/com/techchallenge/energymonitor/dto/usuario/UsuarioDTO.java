package br.com.techchallenge.energymonitor.dto.usuario;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.Usuario;
import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.dto.pessoa.PessoaDto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record UsuarioDTO(
        Long id,
        String nomeCompleto,
        String username,
        String cpf,
        String email,
        Set<PessoaDto> pessoas,
        Set<EnderecoDto> enderecos
) {

    public static UsuarioDTO fromEntity(Usuario usuario) {

        Set<PessoaDto> pessoas = new HashSet<>();
        Set<EnderecoDto> enderecos = new HashSet<>();

        if (usuario.getPessoas() != null) {
            pessoas = usuario.getPessoas().stream().map(Pessoa::toDto).collect(Collectors.toSet());
        }

        if(usuario.getEnderecos() != null) {
            enderecos = usuario.getEnderecos().stream().map(Endereco::toDto).collect(Collectors.toSet());
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getUsername(),
                usuario.getCpf(),
                usuario.getEmail(),
                pessoas,
                enderecos
        );
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO);
    }
}
