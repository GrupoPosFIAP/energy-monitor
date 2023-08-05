package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Endereco;
import br.com.techchallenge.energymonitor.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoDataService extends DataService<Endereco> {

    public EnderecoDataService(EnderecoRepository repository) {
        super(repository);
    }
}
