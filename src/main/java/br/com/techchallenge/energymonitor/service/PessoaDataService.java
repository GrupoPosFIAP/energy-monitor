package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.Usuario;
import br.com.techchallenge.energymonitor.dto.pessoa.PessoaUsuarioDTO;
import br.com.techchallenge.energymonitor.repository.PessoaRepository;
import br.com.techchallenge.energymonitor.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaDataService extends DataService<Pessoa> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaDataService(PessoaRepository repository) {
        super(repository);
    }

    public Pessoa findById(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada."));
    }

    public Page<PessoaUsuarioDTO> findAll(PageRequest pageRequest) {
        return this.pessoaRepository.findAll(pageRequest).map(PessoaUsuarioDTO::fromEntity);
    }

    @Transactional
    public PessoaUsuarioDTO save(PessoaUsuarioDTO pessoaDTO) {
        Usuario usuario = this.usuarioRepository.findUsuarioByUsername(pessoaDTO.username())
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));

        Pessoa pessoa = PessoaUsuarioDTO.toEntity(pessoaDTO);
        pessoa.setUsuario(usuario);

        this.pessoaRepository.save(pessoa);

        return pessoaDTO;
    }
}
