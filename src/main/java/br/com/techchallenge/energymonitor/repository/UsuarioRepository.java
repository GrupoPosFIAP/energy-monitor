package br.com.techchallenge.energymonitor.repository;

import br.com.techchallenge.energymonitor.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findUsuarioByUsername(String username);

}
