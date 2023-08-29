package br.com.techchallenge.energymonitor.dto.usuario;

import br.com.techchallenge.energymonitor.dominio.Usuario;

public record UsuarioBasicoDTO(
        Long id,
        String nomeCompleto,
        String username,
        String cpf,
        String email
) {

    public static UsuarioBasicoDTO fromEntity(Usuario usuario) {
        return new UsuarioBasicoDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getUsername(),
                usuario.getCpf(),
                usuario.getEmail()
        );
    }

    public static Usuario toEntity(UsuarioBasicoDTO usuarioBasicoDTO) {
        return new Usuario(usuarioBasicoDTO);
    }
}
