package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioBasicoDTO;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioDTO;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioEnderecoDTO;
import br.com.techchallenge.energymonitor.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Cria um usuário.",
            description = "Realiza a criação de um usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "201")
    })
    @PostMapping
    public void createUsuario(@RequestBody UsuarioEnderecoDTO usuarioEnderecoDTO) {
        this.usuarioService.createUsuario(usuarioEnderecoDTO);
    }

    @Operation(
            summary = "Consulta todos os usuários.",
            description = "Consulta todos os usuários cadastrados. Se encontrado, retornará uma lista de usuários.")
    @GetMapping
    public Page<UsuarioBasicoDTO> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer pageSize
    ) {
        return this.usuarioService.findAll(PageRequest.of(page, pageSize));
    }

    @Operation(
            summary = "Consulta usuário por ID.",
            description = "Consulta um usuário por ID. Se encontrado, retornará o usuário que possui o ID informado.",
            parameters = @Parameter(name = "id", description = "id do usuário"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UsuarioDTO.class), mediaType = "application/json") })
    })
    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return UsuarioDTO.fromEntity(this.usuarioService.findById(id));
    }

    @Operation(
            summary = "Atualiza um usuário.",
            description = "Realiza a atualização dos dados de um usuário.",
            parameters = @Parameter(name = "id", description = "id do usuário"))
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable Long id, @RequestBody UsuarioEnderecoDTO usuarioEnderecoDTO) {
        this.usuarioService.updateUsuario(id, usuarioEnderecoDTO);
    }

    @Operation(
            summary = "Deleta um usuário.",
            description = "Remove um usuário da base de dados.",
            parameters = @Parameter(name = "id", description = "id do usuário"))
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        this.usuarioService.deleteUsuario(id);
    }

    // ENDERECOS

    @Operation(
            summary = "Atualiza os endereços.",
            description = "Realiza a inseração de endereços em um usuário.",
            parameters = @Parameter(name = "id", description = "id do usuário"))
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @PutMapping("/endereco/{id}")
    public void updateEndereco(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto) {
        this.usuarioService.updateEndereco(id, enderecoDto);
    }

    @Operation(
            summary = "Remoção de endereços.",
            description = "Realiza a remoção do endereço de um usuário.",
            parameters = {@Parameter(name = "id", description = "id do usuário"),
                    @Parameter(name = "enderecoId", description = "id do endereço")})
    @DeleteMapping("/endereco/{id}")
    public void deleteEndereco(@PathVariable Long id, @RequestParam(name = "enderecoId") Long enderecoId) {
        this.usuarioService.deleteEndereco(id, enderecoId);
    }

}
