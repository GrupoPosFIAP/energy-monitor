package br.com.techchallenge.energymonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import br.com.techchallenge.energymonitor.service.PessoaDataService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaDataService dataService;
    
    @PostMapping
    public Dto savePessoa(@Valid @RequestBody PessoaDto pessoa) {
        return dataService.save(pessoa);
    }

    @GetMapping("/{id}")
    public Dto getPessoa(@PathVariable("id") long id) {
        return dataService.get(id);
    }

    @PatchMapping
    public Dto updatePessoa(@Valid @RequestBody PessoaDto pessoa) {
        return dataService.update(pessoa);
    }

    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable("id") long id) {
        dataService.delete(id);
    }
}


