package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.pessoa.PessoaDto;
import br.com.techchallenge.energymonitor.dto.pessoa.PessoaUsuarioDTO;
import br.com.techchallenge.energymonitor.service.PessoaDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaDataService dataService;

    @Operation(
            summary = "Realiza a persistência de uma pessoa.",
            description = "Persiste uma pessoa na base de dados. O objeto persistido será retornado no corpo da resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = PessoaUsuarioDTO.class), mediaType = "application/json") })
    })
    @PostMapping
    public PessoaUsuarioDTO savePessoa(@Valid @RequestBody PessoaUsuarioDTO pessoa) {
        return dataService.save(pessoa);
    }

    @Operation(
            summary = "Consulta pessoa cadastrada pelo ID",
            description = "Consulta pessoa cadastrada pelo ID. Se encontrado, o objeto será retornado no corpo da resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = PessoaDto.class), mediaType = "application/json") })
    })
    @GetMapping("/{id}")
    public Dto getPessoa(@PathVariable("id") Long id) {
        return dataService.get(id);
    }

    @Operation(
            summary = "Consulta todas as pessoas cadastradas",
            description = "Consulta todas as pessoas cadastradas. Se encontrado, retornará uma lista de pessoas.")
    @GetMapping
        public Page<PessoaUsuarioDTO> getPessoas(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer pageSize
    ) {
        return dataService.findAll(PageRequest.of(page, pageSize));
    }

    @Operation(
            summary = "Atualiza uma pessoa",
            description = "Atualiza uma pessoa já cadastrada se o id for encontrado e retornará a pessoa atualizada na resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = PessoaDto.class), mediaType = "application/json") })
    })
    @PutMapping("/{id}")
    public Dto updatePessoa(@PathVariable(required = false, value = "id") Long id, @Valid @RequestBody PessoaDto pessoa) {
        return dataService.update(id, pessoa);
    }

    @Operation(
            summary = "Deleta uma pessoa",
            description = "Remove uma pessoa da base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(mediaType = "application/json") })
    })
    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable("id") Long id) {
        dataService.delete(id);
    }

}


