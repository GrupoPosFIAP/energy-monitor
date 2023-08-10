package br.com.techchallenge.energymonitor.service;

import org.springframework.stereotype.Service;

import br.com.techchallenge.energymonitor.dominio.Endereco;
import br.com.techchallenge.energymonitor.dominio.GrauParentesco;
import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import br.com.techchallenge.energymonitor.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaDataService extends DataService<Pessoa> {

    private BaseRepository<Endereco> enderecoRepository;

    public PessoaDataService(PessoaRepository repository, BaseRepository<Endereco> enderecoRepository) {
        super(repository);
        this.enderecoRepository = enderecoRepository;
    }

    public PessoaDto updateEndereco(Long pessoaId, Long enderecoId) {

        var pessoa = getRepository()
                .findById(pessoaId)
                .orElseThrow(EntityNotFoundException::new);

        var endereco = enderecoRepository
                .findById(pessoaId)
                .orElseThrow(EntityNotFoundException::new);

        pessoa.getEnderecos().add(endereco);

        return getRepository().save(pessoa).toDto();
    }

    public void updateParentesco(long pessoaId, long parenteId, Parentesco grau) {
        Pessoa pessoa = getRepository()
                .findById(pessoaId)
                .orElseThrow(EntityNotFoundException::new);

        Pessoa parente = getRepository()
                .findById(parenteId)
                .orElseThrow(EntityNotFoundException::new);

        pessoa.getParentes().add(new GrauParentesco(pessoa, parente, grau));

        getRepository().save(pessoa);
    }
}
