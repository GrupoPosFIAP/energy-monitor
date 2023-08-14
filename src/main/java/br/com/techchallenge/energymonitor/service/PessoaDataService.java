package br.com.techchallenge.energymonitor.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.techchallenge.energymonitor.dominio.Endereco;
import br.com.techchallenge.energymonitor.dominio.GrauParentesco;
import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import br.com.techchallenge.energymonitor.repository.GrauParentescoRepository;
import br.com.techchallenge.energymonitor.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaDataService extends DataService<Pessoa> {

    private BaseRepository<Endereco> enderecoRepository;

    private GrauParentescoRepository grauParentescoRepository;

    public PessoaDataService(PessoaRepository repository, BaseRepository<Endereco> enderecoRepository,
            GrauParentescoRepository grauParentescoRepository) {
        super(repository);
        this.enderecoRepository = enderecoRepository;
        this.grauParentescoRepository = grauParentescoRepository;
    }

    public PessoaDto updateEndereco(Long pessoaId, Long enderecoId) {

        var pessoa = getRepository()
                .findById(pessoaId)
                .orElseThrow(EntityNotFoundException::new);

        var endereco = enderecoRepository
                .findById(enderecoId)
                .orElseThrow(EntityNotFoundException::new);

        pessoa.getEnderecos().add(endereco);

        return getRepository().save(pessoa).toDto();
    }

    public PessoaDto deleteEndereco(Long pessoaId, Long enderecoId) {

        var pessoa = getRepository()
                .findById(pessoaId)
                .orElseThrow(EntityNotFoundException::new);

        var endereco = enderecoRepository
                .findById(pessoaId)
                .orElseThrow(EntityNotFoundException::new);

        pessoa.getEnderecos().removeIf(e -> endereco.getId().equals(e.getId()));

        return getRepository().save(pessoa).toDto();
    }

    public void updateParentesco(long pessoaId, long parenteId, Parentesco grau) {
        Pessoa pessoa = getRepository()
                .findById(pessoaId)
                .orElseThrow(EntityNotFoundException::new);

        Pessoa parente = getRepository()
                .findById(parenteId)
                .orElseThrow(EntityNotFoundException::new);

        var parentesco = new GrauParentesco(pessoa, parente, grau);

        grauParentescoRepository.save(parentesco);
    }

    public void deleteParentesco(Long pessoaId, Long parenteId) {
        GrauParentesco parentesco = getRepository()
                .findById(pessoaId)
                .map(p -> p.getParentes())
                .stream()
                .flatMap(Set::stream)
                .filter(parente -> parente.getParente().getId().equals(parenteId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        grauParentescoRepository.delete(parentesco);
    }
}
