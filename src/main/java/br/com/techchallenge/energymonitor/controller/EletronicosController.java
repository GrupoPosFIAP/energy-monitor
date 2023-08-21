package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dominio.eletronico.EletronicoFilter;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import br.com.techchallenge.energymonitor.service.DataService;
import br.com.techchallenge.energymonitor.service.EletronicoDataService;
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
@RequestMapping("/eletronicos")
public class EletronicosController {

    @Autowired
    @Qualifier("eletronicoDataService")
    private EletronicoDataService dataService;


    @Operation(summary = "Realiza a persistência de um eletrodoméstico.",
               description = "Persiste um eletrodoméstico na base de dados. O objeto persistido será retornado no corpo da resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @PostMapping
    public Dto saveEletronico(@Valid @RequestBody EletronicoDto eletronico) {
        return dataService.save(eletronico);
    }


    @Operation(summary = "Consulta eletrodoméstico cadastrado pelo ID",
               description = "Consulta eletrodoméstico cadastrado pelo ID. Se encontrado, o objeto será retornado no corpo da resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @GetMapping("/{id}")
    public Dto getEletronico(@PathVariable("") Long id) {
        return dataService.get(id);
    }


    @Operation(summary = "Consulta todos os eletrodomésticos cadastrados",
               description = "Consulta todos os eletrodomésticos cadastrados. Se encontrado, retornará uma lista de eletrodomésticos.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @GetMapping
    public List<Dto> getEletrodomésticos(@RequestParam(required = false) String nome,
                                         @RequestParam(required = false) String modelo) {
        return dataService.findByFilter(new EletronicoFilter(nome, modelo));
    }


    @Operation(summary = "Atualiza um eletrodoméstico",
               description = "Atualiza um eletrodoméstico já cadastrado se o id for encontrado e retornará o eletrodoméstico atualizado na resposta.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @PutMapping("/{id}")
    public Dto updateEletronico(@PathVariable(required = false, value = "id") Long id, @Valid @RequestBody EletronicoDto dto) {
        return dataService.update(id, dto);
    }


    @Operation(summary = "Deleta um eletrodoméstico",
               description = "Remove um eletrodoméstico da base de dados.")
    @ApiResponses({@ApiResponse(responseCode = "204", content = {@Content(mediaType = "application/json")})})
    @DeleteMapping("/{id}")
    public void deleteEletronico(@PathVariable("id") Long id) {
        dataService.delete(id);
    }


    @Operation(summary = "Consulta todos os eletrodomésticos cadastrados",
               description = "Consulta todos os eletrodomésticos cadastrados. Se encontrado, retornará uma lista de eletrodomésticos.")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = EletronicoDto.class), mediaType = "application/json")})})
    @GetMapping
    public List<Dto> getEnderecos() {
        return dataService.getAll();
    }
}
