package br.com.techchallenge.energymonitor.service;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.stereotype.Service;

import br.com.techchallenge.energymonitor.dto.Dto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataService {
    
    public <T extends Dto> void saveData(T request) {
        log.info("Dados recebidos: {}", request);
        log.info("Transformando para classe de domínio {}", request.toDomain());
       
    }
}
