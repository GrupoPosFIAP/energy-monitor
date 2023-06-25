package br.com.techchallenge.energymonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import br.com.techchallenge.energymonitor.service.DataService;

@RestController
@RequestMapping("/eletronicos")
public class EletronicosController {
    @Autowired
    private DataService dataService;

    @PostMapping
    public Dto saveData(@RequestBody EletronicoDto eletronico) {
        return dataService.saveData(eletronico);
    }
}
