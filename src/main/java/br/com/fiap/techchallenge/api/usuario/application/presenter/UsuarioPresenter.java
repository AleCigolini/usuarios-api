package br.com.fiap.techchallenge.api.usuario.application.presenter;

import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;

public interface UsuarioPresenter {
    UsuarioResponseDto toResponse(Usuario usuario);
}
