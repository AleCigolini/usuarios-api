package br.com.fiap.techchallenge.api.usuario.application.presenter;

import br.com.fiap.techchallenge.api.usuario.application.presenter.impl.UsuarioPresenterImpl;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UsuarioPresenterTest {

    private UsuarioPresenterImpl usuarioPresenter;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        usuarioPresenter = new UsuarioPresenterImpl(modelMapper);
    }

    @Test
    void deveConverterUsuarioParaUsuarioResponseDto() {
        Usuario usuario = new Usuario();
        UsuarioResponseDto responseDto = new UsuarioResponseDto();

        when(modelMapper.map(usuario, UsuarioResponseDto.class)).thenReturn(responseDto);

        UsuarioResponseDto resultado = usuarioPresenter.toResponse(usuario);

        assertNotNull(resultado);
        assertEquals(responseDto, resultado);
        verify(modelMapper).map(usuario, UsuarioResponseDto.class);
    }
}
