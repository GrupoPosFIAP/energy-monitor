package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private DataService dataService;

    @Operation(
            summary = "Realiza a persistência de um endereço.",
            description = "Persiste um endereço na base de dados. O objeto persistido será retornado no corpo da resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EnderecoDto.class), mediaType = "application/json") })
    })
    @PostMapping
    public Dto saveData(@RequestBody EnderecoDto endereco) {
    	return dataService.saveData(endereco);
    }
}
	