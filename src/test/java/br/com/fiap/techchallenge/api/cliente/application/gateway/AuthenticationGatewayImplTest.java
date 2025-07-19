package br.com.fiap.techchallenge.api.cliente.application.gateway;

import br.com.fiap.techchallenge.api.cliente.application.gateway.impl.AuthenticationGatewayImpl;
import br.com.fiap.techchallenge.api.cliente.common.interfaces.ClienteAuthentication;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AuthenticationGatewayImplTest {
    private ClienteAuthentication clienteAuthentication;
    private AuthenticationGatewayImpl authenticationGateway;

    @BeforeEach
    void setUp() {
        clienteAuthentication = mock(ClienteAuthentication.class);
        authenticationGateway = new AuthenticationGatewayImpl(clienteAuthentication);
    }

    @Test
    void deveChamarSalvarClienteAuthenticationAoCadastrarCliente() {
        Cliente cliente = new Cliente();
        authenticationGateway.cadastrarCliente(cliente);

        verify(clienteAuthentication, times(1)).salvarClienteAuthentication(cliente);
    }

    @Test
    void deveRetornarTrueQuandoClienteExistente() {
        Cliente cliente = new Cliente();
        when(clienteAuthentication.isClienteExistente(cliente)).thenReturn(true);

        boolean result = authenticationGateway.isClienteExistente(cliente);

        assertThat(result).isTrue();
        verify(clienteAuthentication, times(1)).isClienteExistente(cliente);
    }

    @Test
    void deveRetornarFalseQuandoClienteNaoExistente() {
        Cliente cliente = new Cliente();
        when(clienteAuthentication.isClienteExistente(cliente)).thenReturn(false);

        boolean result = authenticationGateway.isClienteExistente(cliente);

        assertThat(result).isFalse();
        verify(clienteAuthentication, times(1)).isClienteExistente(cliente);
    }
}
