package br.com.fiap.techchallenge.api.usuario.application.usecase;

import br.com.fiap.techchallenge.api.usuario.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.SalvarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioValidacaoException;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalvarUsuarioUseCaseImplTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private AuthenticationGateway authenticationGateway;

    @InjectMocks
    private SalvarUsuarioUseCaseImpl salvarUsuarioUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarUsuario() {
        Usuario usuario = new Usuario();

        when(usuarioGateway.buscarUsuarioPorCpf(usuario.getCpf())).thenReturn(Collections.emptyList());
        when(usuarioGateway.buscarUsuarioPorEmail(usuario.getEmail())).thenReturn(Collections.emptyList());
        when(authenticationGateway.isUsuarioExistente(usuario)).thenReturn(false);
        when(usuarioGateway.salvarUsuario(usuario)).thenReturn(usuario);

        Usuario resultado = salvarUsuarioUseCase.salvarUsuario(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioGateway).salvarUsuario(usuario);
        verify(authenticationGateway).cadastrarUsuario(usuario);
    }

    @Test
    void deveSalvarUsuario_quandoNaoExistirDuplicidadeEAutenticacaoNaoExistir() {
        Usuario usuario = criarUsuario();

        when(usuarioGateway.buscarUsuarioPorCpf(usuario.getCpf())).thenReturn(Collections.emptyList());
        when(usuarioGateway.buscarUsuarioPorEmail(usuario.getEmail())).thenReturn(Collections.emptyList());
        when(authenticationGateway.isUsuarioExistente(usuario)).thenReturn(false);
        when(usuarioGateway.salvarUsuario(usuario)).thenReturn(usuario);

        Usuario resultado = salvarUsuarioUseCase.salvarUsuario(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioGateway).salvarUsuario(usuario);
        verify(authenticationGateway).cadastrarUsuario(usuario);
    }

    @Test
    void deveSalvarUsuario_quandoNaoExistirDuplicidadeEMasAutenticacaoExistir() {
        Usuario usuario = criarUsuario();

        when(usuarioGateway.buscarUsuarioPorCpf(usuario.getCpf())).thenReturn(Collections.emptyList());
        when(usuarioGateway.buscarUsuarioPorEmail(usuario.getEmail())).thenReturn(Collections.emptyList());
        when(authenticationGateway.isUsuarioExistente(usuario)).thenReturn(true);
        when(usuarioGateway.salvarUsuario(usuario)).thenReturn(usuario);

        Usuario resultado = salvarUsuarioUseCase.salvarUsuario(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioGateway).salvarUsuario(usuario);
        verify(authenticationGateway, never()).cadastrarUsuario(usuario);
    }

    @Test
    void naoDeveSalvarUsuario_quandoCpfDuplicado() {
        Usuario usuario = criarUsuario();

        when(usuarioGateway.buscarUsuarioPorCpf(usuario.getCpf()))
                .thenReturn(List.of(criarUsuario()));

        UsuarioValidacaoException exception = assertThrows(UsuarioValidacaoException.class,
                () -> salvarUsuarioUseCase.salvarUsuario(usuario));

        assertTrue(exception.getMessage().contains("Já existe um usuario cadastrado com o CPF informado."));
        verify(usuarioGateway, never()).salvarUsuario(any());
        verify(authenticationGateway, never()).cadastrarUsuario(any());
    }

    @Test
    void naoDeveSalvarUsuario_quandoEmailDuplicado() {
        Usuario usuario = criarUsuario();

        when(usuarioGateway.buscarUsuarioPorCpf(usuario.getCpf())).thenReturn(Collections.emptyList());
        when(usuarioGateway.buscarUsuarioPorEmail(usuario.getEmail()))
                .thenReturn(List.of(criarUsuario()));

        UsuarioValidacaoException exception = assertThrows(UsuarioValidacaoException.class,
                () -> salvarUsuarioUseCase.salvarUsuario(usuario));

        assertTrue(exception.getMessage().contains("Já existe um usuario cadastrado com o e-mail informado."));
        verify(usuarioGateway, never()).salvarUsuario(any());
        verify(authenticationGateway, never()).cadastrarUsuario(any());
    }

    @Test
    void naoDeveSalvarUsuario_quandoCpfEEmailDuplicados() {
        Usuario usuario = criarUsuario();

        when(usuarioGateway.buscarUsuarioPorCpf(usuario.getCpf())).thenReturn(List.of(criarUsuario()));
        when(usuarioGateway.buscarUsuarioPorEmail(usuario.getEmail())).thenReturn(List.of(criarUsuario()));

        UsuarioValidacaoException exception = assertThrows(UsuarioValidacaoException.class,
                () -> salvarUsuarioUseCase.salvarUsuario(usuario));

        assertTrue(exception.getMessage().contains("Já existe um usuario cadastrado com o CPF informado."));
        assertTrue(exception.getMessage().contains("Já existe um usuario cadastrado com o e-mail informado."));
        verify(usuarioGateway, never()).salvarUsuario(any());
        verify(authenticationGateway, never()).cadastrarUsuario(any());
    }

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setCpf(new Cpf("89670770092"));
        usuario.setEmail(new Email("teste@teste.com"));
        return usuario;
    }
}