package br.github.ntidudu.Application.repository;

import br.github.ntidudu.Application.entity.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long > {
}
