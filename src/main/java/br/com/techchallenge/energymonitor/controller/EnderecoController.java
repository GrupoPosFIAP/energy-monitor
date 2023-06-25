package br.com.techchallenge.energymonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.service.DataService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private DataService dataService;
    
    @PostMapping
    public Dto saveData(@RequestBody EnderecoDto endereco) {
    	return dataService.saveData(endereco);
    }
}
	