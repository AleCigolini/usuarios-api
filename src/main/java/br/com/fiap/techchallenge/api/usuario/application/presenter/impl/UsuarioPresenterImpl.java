package br.com.fiap.techchallenge.api.usuario.application.presenter.impl;

import br.com.fiap.techchallenge.api.usuario.application.presenter.UsuarioPresenter;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioPresenterImpl implements UsuarioPresenter {
    private ModelMapper modelMapper;

    public UsuarioResponseDto toResponse(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }
}
