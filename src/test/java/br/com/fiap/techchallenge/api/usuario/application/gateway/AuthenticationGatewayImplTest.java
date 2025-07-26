package br.com.fiap.techchallenge.api.usuario.application.gateway;

import br.com.fiap.techchallenge.api.usuario.application.gateway.impl.AuthenticationGatewayImpl;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioAuthentication;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AuthenticationGatewayImplTest {
    private UsuarioAuthentication usuarioAuthentication;
    private AuthenticationGatewayImpl authenticationGateway;

    @BeforeEach
    void setUp() {
        usuarioAuthentication = mock(UsuarioAuthentication.class);
        authenticationGateway = new AuthenticationGatewayImpl(usuarioAuthentication);
    }

    @Test
    void deveChamarSalvarUsuarioAuthenticationAoCadastrarUsuario() {
        Usuario usuario = new Usuario();
        authenticationGateway.cadastrarUsuario(usuario);

        verify(usuarioAuthentication, times(1)).salvarUsuarioAuthentication(usuario);
    }

    @Test
    void deveRetornarTrueQuandoUsuarioExistente() {
        Usuario usuario = new Usuario();
        when(usuarioAuthentication.isUsuarioExistente(usuario)).thenReturn(true);

        boolean result = authenticationGateway.isUsuarioExistente(usuario);

        assertThat(result).isTrue();
        verify(usuarioAuthentication, times(1)).isUsuarioExistente(usuario);
    }

    @Test
    void deveRetornarFalseQuandoUsuarioNaoExistente() {
        Usuario usuario = new Usuario();
        when(usuarioAuthentication.isUsuarioExistente(usuario)).thenReturn(false);

        boolean result = authenticationGateway.isUsuarioExistente(usuario);

        assertThat(result).isFalse();
        verify(usuarioAuthentication, times(1)).isUsuarioExistente(usuario);
    }
}
