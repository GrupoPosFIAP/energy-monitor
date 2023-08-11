package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.dominio.usuario.Usuario;
import br.com.techchallenge.energymonitor.dominio.usuario.UsuarioBasico;
import br.com.techchallenge.energymonitor.dto.EnderecoDto;
import br.com.techchallenge.energymonitor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public void createUsuario(@RequestBody Usuario usuario) {
        this.usuarioService.createUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> findAll() {
        return this.usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {
        return this.usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable Long id, @RequestBody UsuarioBasico usuarioBasico) {
        this.usuarioService.updateUsuario(id, usuarioBasico);
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
