package br.com.fiap.techchallenge.api.cliente.application.usecase;

import br.com.fiap.techchallenge.api.cliente.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge.api.cliente.application.gateway.ClienteGateway;
import br.com.fiap.techchallenge.api.cliente.application.usecase.impl.SalvarClienteUseCaseImpl;
import br.com.fiap.techchallenge.api.cliente.common.domain.exception.ClienteValidacaoException;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
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

class SalvarClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private AuthenticationGateway authenticationGateway;

    @InjectMocks
    private SalvarClienteUseCaseImpl salvarClienteUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarCliente() {
        Cliente cliente = new Cliente();

        when(clienteGateway.buscarClientePorCpf(cliente.getCpf())).thenReturn(Collections.emptyList());
        when(clienteGateway.buscarClientePorEmail(cliente.getEmail())).thenReturn(Collections.emptyList());
        when(authenticationGateway.isClienteExistente(cliente)).thenReturn(false);
        when(clienteGateway.salvarCliente(cliente)).thenReturn(cliente);

        Cliente resultado = salvarClienteUseCase.salvarCliente(cliente);

        assertEquals(cliente, resultado);
        verify(clienteGateway).salvarCliente(cliente);
        verify(authenticationGateway).cadastrarCliente(cliente);
    }

    @Test
    void deveSalvarCliente_quandoNaoExistirDuplicidadeEAutenticacaoNaoExistir() {
        Cliente cliente = criarCliente();

        when(clienteGateway.buscarClientePorCpf(cliente.getCpf())).thenReturn(Collections.emptyList());
        when(clienteGateway.buscarClientePorEmail(cliente.getEmail())).thenReturn(Collections.emptyList());
        when(authenticationGateway.isClienteExistente(cliente)).thenReturn(false);
        when(clienteGateway.salvarCliente(cliente)).thenReturn(cliente);

        Cliente resultado = salvarClienteUseCase.salvarCliente(cliente);

        assertEquals(cliente, resultado);
        verify(clienteGateway).salvarCliente(cliente);
        verify(authenticationGateway).cadastrarCliente(cliente);
    }

    @Test
    void deveSalvarCliente_quandoNaoExistirDuplicidadeEMasAutenticacaoExistir() {
        Cliente cliente = criarCliente();

        when(clienteGateway.buscarClientePorCpf(cliente.getCpf())).thenReturn(Collections.emptyList());
        when(clienteGateway.buscarClientePorEmail(cliente.getEmail())).thenReturn(Collections.emptyList());
        when(authenticationGateway.isClienteExistente(cliente)).thenReturn(true);
        when(clienteGateway.salvarCliente(cliente)).thenReturn(cliente);

        Cliente resultado = salvarClienteUseCase.salvarCliente(cliente);

        assertEquals(cliente, resultado);
        verify(clienteGateway).salvarCliente(cliente);
        verify(authenticationGateway, never()).cadastrarCliente(cliente);
    }

    @Test
    void naoDeveSalvarCliente_quandoCpfDuplicado() {
        Cliente cliente = criarCliente();

        when(clienteGateway.buscarClientePorCpf(cliente.getCpf()))
                .thenReturn(List.of(criarCliente()));

        ClienteValidacaoException exception = assertThrows(ClienteValidacaoException.class,
                () -> salvarClienteUseCase.salvarCliente(cliente));

        assertTrue(exception.getMessage().contains("Já existe um cliente cadastrado com o CPF informado."));
        verify(clienteGateway, never()).salvarCliente(any());
        verify(authenticationGateway, never()).cadastrarCliente(any());
    }

    @Test
    void naoDeveSalvarCliente_quandoEmailDuplicado() {
        Cliente cliente = criarCliente();

        when(clienteGateway.buscarClientePorCpf(cliente.getCpf())).thenReturn(Collections.emptyList());
        when(clienteGateway.buscarClientePorEmail(cliente.getEmail()))
                .thenReturn(List.of(criarCliente()));

        ClienteValidacaoException exception = assertThrows(ClienteValidacaoException.class,
                () -> salvarClienteUseCase.salvarCliente(cliente));

        assertTrue(exception.getMessage().contains("Já existe um cliente cadastrado com o e-mail informado."));
        verify(clienteGateway, never()).salvarCliente(any());
        verify(authenticationGateway, never()).cadastrarCliente(any());
    }

    @Test
    void naoDeveSalvarCliente_quandoCpfEEmailDuplicados() {
        Cliente cliente = criarCliente();

        when(clienteGateway.buscarClientePorCpf(cliente.getCpf())).thenReturn(List.of(criarCliente()));
        when(clienteGateway.buscarClientePorEmail(cliente.getEmail())).thenReturn(List.of(criarCliente()));

        ClienteValidacaoException exception = assertThrows(ClienteValidacaoException.class,
                () -> salvarClienteUseCase.salvarCliente(cliente));

        assertTrue(exception.getMessage().contains("Já existe um cliente cadastrado com o CPF informado."));
        assertTrue(exception.getMessage().contains("Já existe um cliente cadastrado com o e-mail informado."));
        verify(clienteGateway, never()).salvarCliente(any());
        verify(authenticationGateway, never()).cadastrarCliente(any());
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(new Cpf("89670770092"));
        cliente.setEmail(new Email("teste@teste.com"));
        return cliente;
    }
}