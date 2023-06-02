package br.com.techchallenge.energymonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.service.DataService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private DataService dataService;
    
    @PostMapping
    public ResponseEntity saveData(@RequestBody EnderecoDto endereco) {
        
    	dataService.saveData(endereco);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    	
    }
}
	