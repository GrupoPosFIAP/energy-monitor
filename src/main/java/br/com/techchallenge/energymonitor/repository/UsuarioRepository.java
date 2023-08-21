package br.com.techchallenge.energymonitor.repository;

import br.com.techchallenge.energymonitor.dominio.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
