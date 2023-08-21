package br.com.techchallenge.energymonitor.dominio.usuario;

public record UsuarioBasico(
        String nomeCompleto,
        String username,
        String cpf,
        String email
) {

    public static Usuario toUsuario(UsuarioBasico usuarioBasico) {
        return new Usuario(usuarioBasico);
    }

}
