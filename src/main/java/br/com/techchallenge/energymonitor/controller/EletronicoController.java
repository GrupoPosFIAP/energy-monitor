package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import br.com.techchallenge.energymonitor.dominio.eletronico.EletronicoFilter;
import br.com.techchallenge.energymonitor.dto.ConsumoDTO;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import br.com.techchallenge.energymonitor.service.EletronicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eletronicos")
public class EletronicoController {

    @Autowired
    private EletronicoService eletronicoService;


    @Operation(summary = "Realiza a persistência de um eletrodoméstico.",
               description = "Persiste um eletrodoméstico na base de dados. O objeto persistido será retornado no corpo da resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @PostMapping
    public void createEletronico(@Valid @RequestBody Eletronico eletronico) {
        this.eletronicoService.createEletronico(eletronico);
    }


    @Operation(summary = "Consulta eletrodoméstico cadastrado pelo ID",
               description = "Consulta eletrodoméstico cadastrado pelo ID. Se encontrado, o objeto será retornado no corpo da resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @GetMapping("/{id}")
    public Eletronico findById(@PathVariable("id") Long id) {
        return eletronicoService.findById(id);
    }


    @Operation(summary = "Consulta todos os eletrodomésticos cadastrados",
            description = "Consulta todos os eletrodomésticos cadastrados. Se encontrado, retornará uma lista de eletrodomésticos.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @GetMapping
    public List<Eletronico> findAll() {
        return this.eletronicoService.findAll();
    }


    @Operation(summary = "Consulta todos os eletrodomésticos cadastrados",
               description = "Consulta todos os eletrodomésticos cadastrados. Se encontrado, retornará uma lista de eletrodomésticos.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @GetMapping("/filter")
    public List<Dto> findByFilter(@RequestParam(required = false) String nome,
                                  @RequestParam(required = false) String modelo,
                                  @RequestParam(required = false) int potencia) {
        return eletronicoService.findByFilter(new EletronicoFilter(nome, modelo, potencia));
    }


    @Operation(summary = "Atualiza um eletrodoméstico",
               description = "Atualiza um eletrodoméstico já cadastrado se o id for encontrado e retornará o eletrodoméstico atualizado na resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @PutMapping("/{id}")
    public void updateEletronico(@PathVariable(required = true, value = "id") Long id, @Valid @RequestBody EletronicoDto dto) {
        this.eletronicoService.updateEletronico(id, dto);
    }


    @Operation(summary = "Deleta um eletrodoméstico",
               description = "Remove um eletrodoméstico da base de dados.")
    @ApiResponses({@ApiResponse(responseCode = "204", content = {@Content(mediaType = "application/json")})})
    @DeleteMapping("/{id}")
    public void deleteEletronico(@PathVariable("id") Long id) {
        eletronicoService.deleteEletronico(id);
    }




    // CONSUMO
    @PostMapping("/consumo/{id}")
    public void saveConsumo(@PathVariable Long id, @RequestBody ConsumoDTO consumoDTO) {
        this.eletronicoService.saveConsumo(id, consumoDTO);
    }


    @PutMapping("/consumo/{id}")
    public void updateConsumo(@PathVariable Long id, @RequestParam(name = "consumoId") Long consumoId) {
        this.eletronicoService.updateConsumo(id, consumoId);
    }
}
