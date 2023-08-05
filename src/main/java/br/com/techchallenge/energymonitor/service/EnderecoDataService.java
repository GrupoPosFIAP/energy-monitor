package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Endereco;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("enderecoDataService")
@Primary
public class EnderecoDataService extends DataService<Endereco> {

    public EnderecoDataService(BaseRepository<Endereco> repository) {
        super(repository);
    }
}
