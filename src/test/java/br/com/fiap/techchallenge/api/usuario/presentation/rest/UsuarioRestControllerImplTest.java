package br.com.fiap.techchallenge.api.usuario.presentation.rest;

import br.com.fiap.techchallenge.api.usuario.application.controller.UsuarioController;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioRestControllerImplTest {
    @Mock
    private UsuarioController usuarioController;

    @InjectMocks
    private UsuarioRestControllerImpl usuarioRestController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarUsuarioPorCpf_quandoCpfValido() {
        String cpf = "89670770092";
        UsuarioResponseDto esperado = new UsuarioResponseDto();

        when(usuarioController.buscarUsuarioPorCpf(cpf)).thenReturn(esperado);

        UsuarioResponseDto resultado = usuarioRestController.buscarUsuarioPorCpf(cpf);

        assertEquals(esperado, resultado);
        verify(usuarioController).buscarUsuarioPorCpf(cpf);
    }

    @Test
    void deveBuscarUsuarioPorEmail_quandoEmailValido() {
        String email = "teste@teste.com";
        UsuarioResponseDto esperado = new UsuarioResponseDto();

        when(usuarioController.buscarUsuarioPorEmail(email)).thenReturn(esperado);

        UsuarioResponseDto resultado = usuarioRestController.buscarUsuarioPorEmail(email);

        assertEquals(esperado, resultado);
        verify(usuarioController).buscarUsuarioPorEmail(email);
    }

    @Test
    void deveBuscarUsuarioPorId_quandoIdValido() {
        UUID id = UUID.randomUUID();
        UsuarioResponseDto esperado = new UsuarioResponseDto();

        when(usuarioController.buscarUsuarioPorId(id)).thenReturn(esperado);

        UsuarioResponseDto resultado = usuarioRestController.buscarUsuarioPorId(id);

        assertEquals(esperado, resultado);
        verify(usuarioController).buscarUsuarioPorId(id);
    }

    @Test
    void deveCadastrarUsuario_quandoRequestValido() {
        UsuarioRequestDto requestDto = new UsuarioRequestDto();
        UsuarioResponseDto esperado = new UsuarioResponseDto();

        when(usuarioController.cadastrarUsuario(requestDto)).thenReturn(esperado);

        UsuarioResponseDto resultado = usuarioRestController.cadastrarUsuario(requestDto);

        assertEquals(esperado, resultado);
        verify(usuarioController).cadastrarUsuario(requestDto);
    }

    @Test
    void deveIdentificarUsuario_quandoRequestValido() {
        IdentificarUsuarioDto requestDto = new IdentificarUsuarioDto();
        UsuarioResponseDto esperado = new UsuarioResponseDto();

        when(usuarioController.identificarUsuario(requestDto)).thenReturn(esperado);

        UsuarioResponseDto resultado = usuarioRestController.identificarUsuario(requestDto);

        assertEquals(esperado, resultado);
        verify(usuarioController).identificarUsuario(requestDto);
    }
}
