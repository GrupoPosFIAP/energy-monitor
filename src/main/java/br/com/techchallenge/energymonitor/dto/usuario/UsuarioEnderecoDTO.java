package br.com.techchallenge.energymonitor.dto.usuario;

import br.com.techchallenge.energymonitor.dominio.Usuario;
import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record UsuarioEnderecoDTO(
        @NotBlank(message = "O nome completo é obrigatório.")
        String nomeCompleto,
        @NotBlank(message = "O username é obrigatório.")
        String username,
        @NotBlank(message = "O CPF é obrigatório.")
        //@CPF
        String cpf,
        @NotBlank(message = "O e-mail é obrigatório.")
        //@Email
        String email,
        Set<EnderecoDto> enderecos
) {

    public static UsuarioEnderecoDTO fromEntity(Usuario usuario) {
        Set<EnderecoDto> enderecos = new HashSet<>();

        if(!usuario.getEnderecos().isEmpty()) {
            enderecos = usuario.getEnderecos().stream().map(Endereco::toDto).collect(Collectors.toSet());
        }

        return new UsuarioEnderecoDTO(
                usuario.getNomeCompleto(),
                usuario.getUsername(),
                usuario.getCpf(),
                usuario.getEmail(),
                enderecos
        );
    }

    public static Usuario toEntity(UsuarioEnderecoDTO usuarioDTO) {
        return new Usuario(usuarioDTO);
    }

    public static void mapperDtoToEntity(UsuarioEnderecoDTO usuarioDTO, Usuario usuario) {
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setEmail(usuarioDTO.email());
        usuario.setNomeCompleto(usuarioDTO.nomeCompleto());
        usuario.setUsername(usuarioDTO.username());
    }
}
