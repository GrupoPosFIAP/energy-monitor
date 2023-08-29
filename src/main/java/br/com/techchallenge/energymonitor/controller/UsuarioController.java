package br.com.techchallenge.energymonitor.controller;

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

    @PutMapping("/endereco/{id}")
    public void updateEndereco(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto) {
        this.usuarioService.updateEndereco(id, enderecoDto);
    }

    @DeleteMapping("/endereco/{id}")
    public void deleteEndereco(@PathVariable Long id, @RequestParam(name = "enderecoId") Long enderecoId) {
        this.usuarioService.deleteEndereco(id, enderecoId);
    }

}
