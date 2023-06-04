package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.PessoaDto;
import br.com.techchallenge.energymonitor.service.DataService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private DataService dataService;
    
    @PostMapping
    public void saveData(@Valid @RequestBody PessoaDto pessoa) {
        dataService.saveData(pessoa);
    }
}


