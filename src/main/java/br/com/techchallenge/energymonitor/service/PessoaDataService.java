package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaDataService extends DataService<Pessoa> {

    public PessoaDataService(PessoaRepository repository) {
        super(repository);
    }
}
