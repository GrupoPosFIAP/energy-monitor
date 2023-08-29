package br.com.techchallenge.energymonitor.dto.pessoa;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioBasicoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public record PessoaUsuarioDTO (
        Long id,

        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @NotNull(message = "Data de nascimento deve ser informada")
        @PastOrPresent(message = "Data de nascimento deve ser no passado")
        LocalDate dataNascimento,

        @NotNull
        Genero genero,

        @NotNull
        Parentesco parentesco,

        String username
) {

    public static PessoaUsuarioDTO fromEntity(Pessoa pessoa) {

        Set<UsuarioBasicoDTO> usuarios = new HashSet<>();

        return new PessoaUsuarioDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataNascimento(),
                pessoa.getGenero(),
                pessoa.getParentesco(),
                pessoa.getUsuario() != null ? pessoa.getUsuario().getUsername() : null
        );
    }

    public static Pessoa toEntity(PessoaUsuarioDTO pessoaDTO) {
        return new Pessoa(pessoaDTO);
    }

    public static void mapperDtoToEntity(PessoaUsuarioDTO pessoaUsuarioDTO, Pessoa pessoa) {
        pessoa.setDataNascimento(pessoaUsuarioDTO.dataNascimento());
        pessoa.setNome(pessoaUsuarioDTO.nome());
        pessoa.setGenero(pessoaUsuarioDTO.genero());
        pessoa.setParentesco(pessoaUsuarioDTO.parentesco());
    }
}
