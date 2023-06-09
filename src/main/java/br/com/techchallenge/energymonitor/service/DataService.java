package br.com.techchallenge.energymonitor.service;

import org.springframework.stereotype.Service;

import br.com.techchallenge.energymonitor.dto.Dto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataService {

    public Dto saveData(Dto request) {
        log.info("Dados recebidos: {}", request);
        log.info("Transformando para classe de domínio {}", request.toDomain());
        return request;
    }
}
