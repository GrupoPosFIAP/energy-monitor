package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Usuario;
import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioBasicoDTO;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioEnderecoDTO;
import br.com.techchallenge.energymonitor.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoDataService enderecoService;

    @Autowired
    private PessoaDataService pessoaService;

    @Transactional
    public void createUsuario(UsuarioEnderecoDTO usuarioEnderecoDTO) {
        var usuario = UsuarioEnderecoDTO.toEntity(usuarioEnderecoDTO);
        this.usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioBasicoDTO> findAll(PageRequest pageRequest) {
        var usuarios = this.usuarioRepository.findAll(pageRequest);
        return usuarios.map(UsuarioBasicoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));
    }

    @Transactional
    public void updateUsuario(Long id, UsuarioEnderecoDTO usuarioEnderecoDTO) {
        Usuario usuario = findById(id);

        UsuarioEnderecoDTO.mapperDtoToEntity(usuarioEnderecoDTO, usuario);

        this.usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteUsuario(Long id) {
        Usuario usuario = findById(id);
        this.usuarioRepository.delete(usuario);
    }

    @Transactional
    public void updateEndereco(Long id, EnderecoDto enderecoDto) {
        Usuario usuario = findById(id);

        usuario.addEndereco(enderecoDto.toDomain());

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteEndereco(Long id, Long enderecoId) {
        if (enderecoId == null) {
            throw new RuntimeException("ID do endereço inválido.");
        }

        Endereco endereco = findEnderecoById(enderecoId);
        Usuario usuario = findById(id);

        if (!usuario.removeEndereco(endereco)) {
            throw new EntityNotFoundException("Endereço informado é inválido ou não faz parte do usuário.");
        }

        this.usuarioRepository.save(usuario);

    }

    private Endereco findEnderecoById(Long enderecosIds) {
        return (Endereco) this.enderecoService.get(enderecosIds).toDomain();
    }

    @Transactional
    public void includeEletronico(Long id, EletronicoDto eletronicoDto) {
        Usuario usuario = findById(id);

        usuario.addEletronico(eletronicoDto.toDomain());

        usuarioRepository.save(usuario);
    }

    public void listarEletronicos(Long id, PageRequest pageRequest) {
        findById(id);

    }
}
