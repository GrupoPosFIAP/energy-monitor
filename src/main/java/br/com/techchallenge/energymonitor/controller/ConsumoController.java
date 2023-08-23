package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.ConsumoDTO;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.repository.consumo.ConsumoRepository;
import br.com.techchallenge.energymonitor.repository.consumo.ConsumoUpdateRepository;
import br.com.techchallenge.energymonitor.service.ConsumoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/consumo")
public class ConsumoController {

    @Autowired
    @Qualifier("consumoDataService")
    private ConsumoService consumoService;

    @Autowired
    private ConsumoUpdateRepository consumoUpdateRepository;

    @Operation(summary     = "Realiza a persistência do Painel de Consumo.",
               description = "Persisite o consumo na base de dados. O objeto persistido será retornado no corpo da resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ConsumoDTO.class), mediaType = "application/json")})})
    @PostMapping
    public Dto saveConsumo(@Valid @RequestBody ConsumoDTO dto) {
        return consumoService.save(dto);
    }


    @Operation(summary     = "Consulta consumo cadastrado pelo ID",
               description = "Consulta consumo cadastrado pelo ID. Se encontrado, o objeto será retornado no corpo da resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ConsumoDTO.class), mediaType = "application/json")})})
    @GetMapping("/{id}")
    public Dto getConsumo(@PathVariable("id") Long id) {
        return consumoService.get(id);
    }


    @Operation(summary     = "Consulta todos os consumos cadastrados",
               description = "Consulta todos os consumos cadastrados. Se encontrado, retornará uma lista de consumo.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ConsumoDTO.class), mediaType = "application/json")})})
    @GetMapping
    public List<Dto> getConsumos() {
        return consumoService.getAll();
    }


    @Operation(summary     = "Atualiza e calcula consumo de eletrônico",
               description = "Atualiza e calcula consumo de eletrônico. Se id for encontrado atualiza e retorna consumo atualizado na resposta.")
    @ApiResponses({@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = ConsumoDTO.class), mediaType = "application/json")})})
    @PutMapping("/{id}")
    public Dto updateConsumo(@PathVariable(value = "id") Long id, @Valid @RequestBody ConsumoDTO dto) {
        return consumoUpdateRepository.updateConsumo(id, dto);
    }
}
