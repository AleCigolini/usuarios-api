package br.com.fiap.techchallenge.api.usuario.application.usecase;

import br.com.fiap.techchallenge.api.role.domain.Role;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;

public interface SalvarUsuarioUseCase {
    Usuario salvarUsuario(Usuario usuarioRequestDto, Role role);
}
