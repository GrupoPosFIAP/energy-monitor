package br.com.techchallenge.energymonitor.dominio.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioBasico {

    private String nomeCompleto;
    private String username;
    private String cpf;
    private String email;

}
