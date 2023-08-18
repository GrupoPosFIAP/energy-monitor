package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dominio.endereco.EnderecoFilter;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import br.com.techchallenge.energymonitor.repository.endereco.EnderecoCustomRepository;
import br.com.techchallenge.energymonitor.repository.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("enderecoDataService")
@Primary
public class EnderecoDataService extends DataService<Endereco> {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    EnderecoCustomRepository customRepository;

    public EnderecoDataService(BaseRepository<Endereco> repository) {
        super(repository);
    }

    public List<Dto> findByFilter(EnderecoFilter filter) {
        return customRepository.findEnderecoFiltered(filter).stream().map(Domain::toDto).toList();
    }
}
