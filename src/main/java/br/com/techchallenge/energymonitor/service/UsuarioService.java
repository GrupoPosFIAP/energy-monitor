package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Endereco;
import br.com.techchallenge.energymonitor.dominio.usuario.Usuario;
import br.com.techchallenge.energymonitor.dominio.usuario.UsuarioBasico;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoDataService enderecoService;

    public void createUsuario(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));
    }

    public void updateUsuario(Long id, UsuarioBasico usuarioBasico) {
        Usuario usuario = findById(id);

        usuario.setCpf(usuarioBasico.getCpf());
        usuario.setEmail(usuarioBasico.getEmail());
        usuario.setNomeCompleto(usuarioBasico.getNomeCompleto());
        usuario.setUsername(usuarioBasico.getUsername());

        this.usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = findById(id);
        this.usuarioRepository.delete(usuario);
    }

    public void updateEndereco(Long id, EnderecoDto enderecoDto) {
        Usuario usuario = findById(id);

        usuario.addEndereco(enderecoDto.toDomain());

        usuarioRepository.save(usuario);
    }

    public void deleteEndereco(Long id, Long enderecoId) {
        if(enderecoId == null) {
            throw new RuntimeException("ID do endereço inválido.");
        }

        Endereco endereco = findEnderecoById(enderecoId);
        Usuario usuario = findById(id);

        if(!usuario.removeEndereco(endereco)) {
            throw new RuntimeException("Endereço informado é inválido ou não faz parte do usuário.");
        }

        this.usuarioRepository.save(usuario);

    }

    private Endereco findEnderecoById(Long enderecosIds) {
        return (Endereco) this.enderecoService.get(enderecosIds).toDomain();
    }
}
