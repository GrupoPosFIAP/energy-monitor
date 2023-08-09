package br.com.techchallenge.energymonitor.service;

import org.springframework.stereotype.Service;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import br.com.techchallenge.energymonitor.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaDataService extends DataService<Pessoa> {

    public PessoaDataService(PessoaRepository repository) {
        super(repository);
    }

    public PessoaDto updateEndereco(Long id, EnderecoDto endereco) {
        var pessoa = getRepository()
            .findById(id)
            .orElseThrow(EntityNotFoundException::new);

        pessoa.getEnderecos().add(endereco.toDomain());

        return getRepository().save(pessoa).toDto();
    }
}
