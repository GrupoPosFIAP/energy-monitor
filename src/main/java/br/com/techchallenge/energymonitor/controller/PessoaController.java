package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.PessoaDto;
import br.com.techchallenge.energymonitor.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Realiza a persistência de uma pessoa.",
            description = "Persiste uma pessoa na base de dados. O objeto persistido será retornado no corpo da resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = PessoaDto.class), mediaType = "application/json") })
    })
    @PostMapping
    public Dto saveData(@Valid @RequestBody PessoaDto pessoa) {
        return dataService.saveData(pessoa);
    }

}


