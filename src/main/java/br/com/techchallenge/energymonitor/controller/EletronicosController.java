package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import br.com.techchallenge.energymonitor.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eletronicos")
public class EletronicosController {
    @Autowired
    private DataService dataService;

    @PostMapping
    public void saveData(@ResponseBody EletronicoDto eletronico) {

    	dataService.saveData(eletronico);

    }
}
