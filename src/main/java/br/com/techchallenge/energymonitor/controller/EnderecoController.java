package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dominio.endereco.EnderecoFilter;
import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.service.EnderecoDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    @Qualifier("enderecoDataService")
    private EnderecoDataService dataService;

    @Operation(
            summary = "Realiza a persistência de um endereço.",
            description = "Persiste um endereço na base de dados. O objeto persistido será retornado no corpo da resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EnderecoDto.class), mediaType = "application/json") })
    })
    @PostMapping
    public Dto saveEndereco(@Valid @RequestBody EnderecoDto endereco) {
        return dataService.save(endereco);
    }

    @Operation(
            summary = "Consulta endereço cadastrado pelo ID",
            description = "Consulta endereço cadastrado pelo ID. Se encontrado, o objeto será retornado no corpo da resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EnderecoDto.class), mediaType = "application/json") })
    })
    @GetMapping("/{id}")
    public Dto getEndereco(@PathVariable("id") Long id) {
        return dataService.get(id);
    }

    @Operation(
            summary = "Consulta todos os endereços cadastrados",
            description = "Consulta todos os endereços cadastrados. Se encontrado, retornará uma lista de endereços.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EnderecoDto.class), mediaType = "application/json") })
    })
    @GetMapping
    public List<Dto> getEnderecos(@RequestParam(required = false) String rua,
                                  @RequestParam(required = false) String bairro,
                                  @RequestParam(required = false) String cidade,
                                  @RequestParam(required = false) Estado estado) {
        return dataService.findByFilter(new EnderecoFilter(rua, bairro, cidade, estado));
    }

    @Operation(
            summary = "Atualiza um endereço",
            description = "Atualiza um endereço já cadastrado se o id for encontrado e retornará o endereço atualizado na resposta.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EnderecoDto.class), mediaType = "application/json") })
    })
    @PutMapping("/{id}")
    public Dto updateEndereco(@PathVariable(required = false, value = "id") Long id, @Valid @RequestBody EnderecoDto pessoa) {
        return dataService.update(id, pessoa);
    }

    @Operation(
            summary = "Deleta uma pessoa",
            description = "Remove uma pessoa da base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(mediaType = "application/json") })
    })
    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable("id") Long id) {
        dataService.delete(id);
    }
}
	