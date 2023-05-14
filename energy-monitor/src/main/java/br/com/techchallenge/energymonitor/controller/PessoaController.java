package br.com.techchallenge.energymonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techchallenge.energymonitor.dto.PessoaDto;
import br.com.techchallenge.energymonitor.service.DataService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private DataService dataService;
    
    @GetMapping
    public void saveData(@RequestBody PessoaDto pessoa) {
        dataService.saveData(pessoa);
    }
}
