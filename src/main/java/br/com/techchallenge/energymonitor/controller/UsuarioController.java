package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioBasicoDTO;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioDTO;
import br.com.techchallenge.energymonitor.dto.usuario.UsuarioEnderecoDTO;
import br.com.techchallenge.energymonitor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public void createUsuario(@RequestBody UsuarioEnderecoDTO usuarioEnderecoDTO) {
        this.usuarioService.createUsuario(usuarioEnderecoDTO);
    }

    @GetMapping
    public Page<UsuarioBasicoDTO> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer pageSize
    ) {
        return this.usuarioService.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return UsuarioDTO.fromEntity(this.usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable Long id, @RequestBody UsuarioEnderecoDTO usuarioEnderecoDTO) {
        this.usuarioService.updateUsuario(id, usuarioEnderecoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        this.usuarioService.deleteUsuario(id);
    }

    // ENDERECOS

    @PutMapping("/{id}/endereco")
    public void updateEndereco(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto) {
        this.usuarioService.updateEndereco(id, enderecoDto);
    }

    @DeleteMapping("/{id}/endereco")
    public void deleteEndereco(@PathVariable Long id, @RequestParam(name = "enderecoId") Long enderecoId) {
        this.usuarioService.deleteEndereco(id, enderecoId);
    }

    // ELETRONICOS

    @PutMapping("/{id}/eletronico")
    public void createEletronico(@PathVariable Long id, @RequestBody EletronicoDto eletronicoDto) {
        this.usuarioService.includeEletronico(id, eletronicoDto);
    }

    @GetMapping("{/id}/eletronico")
    public void listarEletronicos(@PathVariable Long id,
                                                        @RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "25") Integer pageSize) {
        this.usuarioService.listarEletronicos(id, PageRequest.of(page, pageSize));
    }
}
