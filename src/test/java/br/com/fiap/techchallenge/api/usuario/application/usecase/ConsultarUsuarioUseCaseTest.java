package br.com.fiap.techchallenge.api.usuario.application.usecase;

import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.ConsultarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioValidacaoException;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultarUsuarioUseCaseTest {
    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private ConsultarUsuarioUseCaseImpl useCase;

    private Cpf cpf;
    private Email email;
    private UUID id;
    private Usuario usuario;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cpf = new Cpf("89670770092");
        email = new Email("teste@teste.com");
        id = UUID.randomUUID();

        usuario = mock(Usuario.class);
    }

    @Test
    void deveMapearParaBuscarUsuarioPorCpf_quandoUsuarioExistir() {
        List<Usuario> lista = new LinkedList<>();
        lista.add(usuario);

        when(usuarioGateway.buscarUsuarioPorCpf(cpf)).thenReturn(lista);

        Usuario resultado = useCase.buscarUsuarioPorCpf(cpf);

        assertEquals(usuario, resultado);
        verify(usuarioGateway).buscarUsuarioPorCpf(cpf);
    }

    @Test
    void deveMapearParaBuscarUsuarioPorCpf_quandoListaVazia_deveLancarException() {
        when(usuarioGateway.buscarUsuarioPorCpf(cpf)).thenReturn(Collections.emptyList());

        UsuarioNaoEncontradoException ex = assertThrows(UsuarioNaoEncontradoException.class,
                () -> useCase.buscarUsuarioPorCpf(cpf));
        assertTrue(ex.getMessage().contains("cpf"));
    }

    @Test
    void deveMapearParaBuscarUsuarioPorCpf_quandoMaisDeUmUsuarioLancarException() {
        List<Usuario> lista = Arrays.asList(usuario, usuario);
        when(usuarioGateway.buscarUsuarioPorCpf(cpf)).thenReturn(lista);

        UsuarioValidacaoException ex = assertThrows(UsuarioValidacaoException.class,
                () -> useCase.buscarUsuarioPorCpf(cpf));
        assertTrue(ex.getMessage().contains("cpf"));
    }

    @Test
    void deveMapearParaBuscarUsuarioPorId_quandoUsuarioExistir() {
        when(usuarioGateway.buscarUsuarioPorId(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = useCase.buscarUsuarioPorId(id);

        assertEquals(usuario, resultado);
        verify(usuarioGateway).buscarUsuarioPorId(id);
    }

    @Test
    void deveMapearParaBuscarUsuarioPorId_quandoUsuarioNaoExistirLancarException() {
        when(usuarioGateway.buscarUsuarioPorId(id)).thenReturn(Optional.empty());

        UsuarioNaoEncontradoException ex = assertThrows(UsuarioNaoEncontradoException.class,
                () -> useCase.buscarUsuarioPorId(id));
        assertTrue(ex.getMessage().contains("id"));
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmail_quandoUsuarioExistir() {
        List<Usuario> lista = new LinkedList<>();
        lista.add(usuario);

        when(usuarioGateway.buscarUsuarioPorEmail(email)).thenReturn(lista);

        Usuario resultado = useCase.buscarUsuarioPorEmail(email);

        assertEquals(usuario, resultado);
        verify(usuarioGateway).buscarUsuarioPorEmail(email);
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmail_quandoListaVaziaLancarException() {
        when(usuarioGateway.buscarUsuarioPorEmail(email)).thenReturn(Collections.emptyList());

        UsuarioNaoEncontradoException ex = assertThrows(UsuarioNaoEncontradoException.class,
                () -> useCase.buscarUsuarioPorEmail(email));
        assertTrue(ex.getMessage().contains("email"));
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmail_quandoMaisDeUmUsuarioLancarException() {
        List<Usuario> lista = Arrays.asList(usuario, usuario);

        when(usuarioGateway.buscarUsuarioPorEmail(email)).thenReturn(lista);

        UsuarioValidacaoException ex = assertThrows(UsuarioValidacaoException.class,
                () -> useCase.buscarUsuarioPorEmail(email));
        assertTrue(ex.getMessage().contains("email"));
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmailCpf_quandoCpfEEmailForNullLancarException() {
        UsuarioValidacaoException ex = assertThrows(UsuarioValidacaoException.class,
                () -> useCase.buscarUsuarioPorEmailCpf(null, null, true));
        assertTrue(ex.getMessage().contains("Pelo menos um dos campos"));
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmailCpf_quandoNaoEncontrarEThrowTrueLancarException() {
        when(usuarioGateway.buscarUsuarioPorEmailECpf(email, cpf)).thenReturn(Collections.emptyList());

        UsuarioNaoEncontradoException ex = assertThrows(UsuarioNaoEncontradoException.class,
                () -> useCase.buscarUsuarioPorEmailCpf(cpf, email, true));
        assertTrue(ex.getMessage().contains("Não foi encontrado nenhum usuario"));
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmailCpf_quandoNaoEncontrarEThrowFalseRetornarNull() {
        when(usuarioGateway.buscarUsuarioPorEmailECpf(email, cpf)).thenReturn(Collections.emptyList());

        Usuario resultado = useCase.buscarUsuarioPorEmailCpf(cpf, email, false);

        assertNull(resultado);
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmailCpf_quandoEncontrarRetornarUsuario() {
        List<Usuario> lista = new LinkedList<>();
        lista.add(usuario);

        when(usuarioGateway.buscarUsuarioPorEmailECpf(email, cpf)).thenReturn(lista);

        Usuario resultado = useCase.buscarUsuarioPorEmailCpf(cpf, email, true);

        assertEquals(usuario, resultado);
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmailCpf_quandoEncontrarViaCpfRetornarUsuario() {
        List<Usuario> lista = new LinkedList<>();
        lista.add(usuario);

        when(usuarioGateway.buscarUsuarioPorCpf(cpf)).thenReturn(lista);

        Usuario resultado = useCase.buscarUsuarioPorEmailCpf(cpf, null, true);

        assertEquals(usuario, resultado);
    }

    @Test
    void deveMapearParaBuscarUsuarioPorEmailCpf_quandoEncontrarViaEmailRetornarUsuario() {
        List<Usuario> lista = new LinkedList<>();
        lista.add(usuario);

        when(usuarioGateway.buscarUsuarioPorEmail(email)).thenReturn(lista);

        Usuario resultado = useCase.buscarUsuarioPorEmailCpf(null, email, true);

        assertEquals(usuario, resultado);
    }
}
