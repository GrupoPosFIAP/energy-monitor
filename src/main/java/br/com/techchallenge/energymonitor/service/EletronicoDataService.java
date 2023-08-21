package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import br.com.techchallenge.energymonitor.dominio.eletronico.EletronicoFilter;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import br.com.techchallenge.energymonitor.repository.eletronico.EletronicoCustomRepository;
import br.com.techchallenge.energymonitor.repository.eletronico.EletronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service("eletronicoDataService")
public class EletronicoDataService extends DataService<Eletronico> {

    @Autowired
    EletronicoRepository repository;

    @Autowired
    EletronicoCustomRepository customRepository;

    public EletronicoDataService(BaseRepository<Eletronico> repository) {
        super(repository);
    }

    public List<Dto> findByFilter(EletronicoFilter filter) {
        return customRepository.findEletronicoFiltered(filter).stream().map(Domain::toDto).toList();
    }
}
