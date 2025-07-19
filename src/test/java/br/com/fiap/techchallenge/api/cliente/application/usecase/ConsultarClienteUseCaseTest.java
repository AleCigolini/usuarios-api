package br.com.fiap.techchallenge.api.cliente.application.usecase;

import br.com.fiap.techchallenge.api.cliente.application.gateway.ClienteGateway;
import br.com.fiap.techchallenge.api.cliente.application.usecase.impl.ConsultarClienteUseCaseImpl;
import br.com.fiap.techchallenge.api.cliente.common.domain.exception.ClienteNaoEncontradoException;
import br.com.fiap.techchallenge.api.cliente.common.domain.exception.ClienteValidacaoException;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
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

public class ConsultarClienteUseCaseTest {
    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ConsultarClienteUseCaseImpl useCase;

    private Cpf cpf;
    private Email email;
    private UUID id;
    private Cliente cliente;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cpf = new Cpf("89670770092");
        email = new Email("teste@teste.com");
        id = UUID.randomUUID();

        cliente = mock(Cliente.class);
    }

    @Test
    void deveMapearParaBuscarClientePorCpf_quandoClienteExistir() {
        List<Cliente> lista = new LinkedList<>();
        lista.add(cliente);

        when(clienteGateway.buscarClientePorCpf(cpf)).thenReturn(lista);

        Cliente resultado = useCase.buscarClientePorCpf(cpf);

        assertEquals(cliente, resultado);
        verify(clienteGateway).buscarClientePorCpf(cpf);
    }

    @Test
    void deveMapearParaBuscarClientePorCpf_quandoListaVazia_deveLancarException() {
        when(clienteGateway.buscarClientePorCpf(cpf)).thenReturn(Collections.emptyList());

        ClienteNaoEncontradoException ex = assertThrows(ClienteNaoEncontradoException.class,
                () -> useCase.buscarClientePorCpf(cpf));
        assertTrue(ex.getMessage().contains("cpf"));
    }

    @Test
    void deveMapearParaBuscarClientePorCpf_quandoMaisDeUmClienteLancarException() {
        List<Cliente> lista = Arrays.asList(cliente, cliente);
        when(clienteGateway.buscarClientePorCpf(cpf)).thenReturn(lista);

        ClienteValidacaoException ex = assertThrows(ClienteValidacaoException.class,
                () -> useCase.buscarClientePorCpf(cpf));
        assertTrue(ex.getMessage().contains("cpf"));
    }

    @Test
    void deveMapearParaBuscarClientePorId_quandoClienteExistir() {
        when(clienteGateway.buscarClientePorId(id)).thenReturn(Optional.of(cliente));

        Cliente resultado = useCase.buscarClientePorId(id);

        assertEquals(cliente, resultado);
        verify(clienteGateway).buscarClientePorId(id);
    }

    @Test
    void deveMapearParaBuscarClientePorId_quandoClienteNaoExistirLancarException() {
        when(clienteGateway.buscarClientePorId(id)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException ex = assertThrows(ClienteNaoEncontradoException.class,
                () -> useCase.buscarClientePorId(id));
        assertTrue(ex.getMessage().contains("id"));
    }

    @Test
    void deveMapearParaBuscarClientePorEmail_quandoClienteExistir() {
        List<Cliente> lista = new LinkedList<>();
        lista.add(cliente);

        when(clienteGateway.buscarClientePorEmail(email)).thenReturn(lista);

        Cliente resultado = useCase.buscarClientePorEmail(email);

        assertEquals(cliente, resultado);
        verify(clienteGateway).buscarClientePorEmail(email);
    }

    @Test
    void deveMapearParaBuscarClientePorEmail_quandoListaVaziaLancarException() {
        when(clienteGateway.buscarClientePorEmail(email)).thenReturn(Collections.emptyList());

        ClienteNaoEncontradoException ex = assertThrows(ClienteNaoEncontradoException.class,
                () -> useCase.buscarClientePorEmail(email));
        assertTrue(ex.getMessage().contains("email"));
    }

    @Test
    void deveMapearParaBuscarClientePorEmail_quandoMaisDeUmClienteLancarException() {
        List<Cliente> lista = Arrays.asList(cliente, cliente);

        when(clienteGateway.buscarClientePorEmail(email)).thenReturn(lista);

        ClienteValidacaoException ex = assertThrows(ClienteValidacaoException.class,
                () -> useCase.buscarClientePorEmail(email));
        assertTrue(ex.getMessage().contains("email"));
    }

    @Test
    void deveMapearParaBuscarClientePorEmailCpf_quandoCpfEEmailForNullLancarException() {
        ClienteValidacaoException ex = assertThrows(ClienteValidacaoException.class,
                () -> useCase.buscarClientePorEmailCpf(null, null, true));
        assertTrue(ex.getMessage().contains("Pelo menos um dos campos"));
    }

    @Test
    void deveMapearParaBuscarClientePorEmailCpf_quandoNaoEncontrarEThrowTrueLancarException() {
        when(clienteGateway.buscarClientePorEmailECpf(email, cpf)).thenReturn(Collections.emptyList());

        ClienteNaoEncontradoException ex = assertThrows(ClienteNaoEncontradoException.class,
                () -> useCase.buscarClientePorEmailCpf(cpf, email, true));
        assertTrue(ex.getMessage().contains("Não foi encontrado nenhum cliente"));
    }

    @Test
    void deveMapearParaBuscarClientePorEmailCpf_quandoNaoEncontrarEThrowFalseRetornarNull() {
        when(clienteGateway.buscarClientePorEmailECpf(email, cpf)).thenReturn(Collections.emptyList());

        Cliente resultado = useCase.buscarClientePorEmailCpf(cpf, email, false);

        assertNull(resultado);
    }

    @Test
    void deveMapearParaBuscarClientePorEmailCpf_quandoEncontrarRetornarCliente() {
        List<Cliente> lista = new LinkedList<>();
        lista.add(cliente);

        when(clienteGateway.buscarClientePorEmailECpf(email, cpf)).thenReturn(lista);

        Cliente resultado = useCase.buscarClientePorEmailCpf(cpf, email, true);

        assertEquals(cliente, resultado);
    }

    @Test
    void deveMapearParaBuscarClientePorEmailCpf_quandoEncontrarViaCpfRetornarCliente() {
        List<Cliente> lista = new LinkedList<>();
        lista.add(cliente);

        when(clienteGateway.buscarClientePorCpf(cpf)).thenReturn(lista);

        Cliente resultado = useCase.buscarClientePorEmailCpf(cpf, null, true);

        assertEquals(cliente, resultado);
    }

    @Test
    void deveMapearParaBuscarClientePorEmailCpf_quandoEncontrarViaEmailRetornarCliente() {
        List<Cliente> lista = new LinkedList<>();
        lista.add(cliente);

        when(clienteGateway.buscarClientePorEmail(email)).thenReturn(lista);

        Cliente resultado = useCase.buscarClientePorEmailCpf(null, email, true);

        assertEquals(cliente, resultado);
    }
}
