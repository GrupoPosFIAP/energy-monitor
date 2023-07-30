package br.com.techchallenge.energymonitor.service;

import org.springframework.stereotype.Service;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.repository.PessoaRepository;

@Service
public class PessoaDataService extends DataService<Pessoa> {

    public PessoaDataService(PessoaRepository repository) {
        super(repository);
    }
}
