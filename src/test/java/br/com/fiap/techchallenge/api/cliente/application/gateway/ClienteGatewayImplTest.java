package br.com.fiap.techchallenge.api.cliente.application.gateway;

import br.com.fiap.techchallenge.api.cliente.application.gateway.impl.ClienteGatewayImpl;
import br.com.fiap.techchallenge.api.cliente.application.mapper.DatabaseClienteMapper;
import br.com.fiap.techchallenge.api.cliente.common.domain.entity.JpaClienteEntity;
import br.com.fiap.techchallenge.api.cliente.common.interfaces.ClienteDatabase;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ClienteGatewayImplTest {
    private static final String CPF = "89670770092";

    private ClienteDatabase clienteDatabase;
    private DatabaseClienteMapper mapper;
    private ClienteGatewayImpl clienteGateway;

    @BeforeEach
    void setUp() {
        clienteDatabase = mock(ClienteDatabase.class);
        mapper = mock(DatabaseClienteMapper.class);
        clienteGateway = new ClienteGatewayImpl(clienteDatabase, mapper);
    }

    @Test
    void deveSalvarCliente() {
        Cliente cliente = new Cliente();
        JpaClienteEntity clienteEntity = new JpaClienteEntity();
        when(mapper.toJpaClienteEntity(cliente)).thenReturn(clienteEntity);
        when(clienteDatabase.salvarCliente(clienteEntity)).thenReturn(clienteEntity);
        when(mapper.toCliente(clienteEntity)).thenReturn(cliente);

        Cliente result = clienteGateway.salvarCliente(cliente);

        assertThat(result).isEqualTo(cliente);
        verify(mapper).toJpaClienteEntity(cliente);
        verify(clienteDatabase).salvarCliente(clienteEntity);
        verify(mapper).toCliente(clienteEntity);
    }

    @Test
    void deveBuscarClientePorCpf() {
        Cpf cpf = new Cpf(CPF);
        JpaClienteEntity entity = new JpaClienteEntity();
        Cliente cliente = new Cliente();
        when(clienteDatabase.buscarClientePorCpf(cpf.getValue())).thenReturn(List.of(entity));
        when(mapper.toCliente(entity)).thenReturn(cliente);

        List<Cliente> result = clienteGateway.buscarClientePorCpf(cpf);

        assertThat(result).containsExactly(cliente);
        verify(clienteDatabase).buscarClientePorCpf(cpf.getValue());
        verify(mapper).toCliente(entity);
    }

    @Test
    void deveBuscarClientePorId() {
        UUID id = UUID.randomUUID();
        JpaClienteEntity entity = new JpaClienteEntity();
        Cliente cliente = new Cliente();
        when(clienteDatabase.buscarClientePorId(id)).thenReturn(Optional.of(entity));
        when(mapper.toCliente(entity)).thenReturn(cliente);

        Optional<Cliente> result = clienteGateway.buscarClientePorId(id);

        assertThat(result).contains(cliente);
        verify(clienteDatabase).buscarClientePorId(id);
        verify(mapper).toCliente(entity);
    }

    @Test
    void deveBuscarClientePorEmail() {
        Email email = new Email("teste@teste.com");
        JpaClienteEntity entity = new JpaClienteEntity();
        Cliente cliente = new Cliente();
        when(clienteDatabase.buscarClientePorEmail(email.getEndereco())).thenReturn(List.of(entity));
        when(mapper.toCliente(entity)).thenReturn(cliente);

        List<Cliente> result = clienteGateway.buscarClientePorEmail(email);

        assertThat(result).containsExactly(cliente);
        verify(clienteDatabase).buscarClientePorEmail(email.getEndereco());
        verify(mapper).toCliente(entity);
    }

    @Test
    void deveBuscarClientePorEmailECpf() {
        Email email = new Email("teste@teste.com");
        Cpf cpf = new Cpf(CPF);
        JpaClienteEntity entity = new JpaClienteEntity();
        Cliente cliente = new Cliente();
        when(clienteDatabase.buscarClientePorEmailECpf(email.getEndereco(), cpf.getValue())).thenReturn(List.of(entity));
        when(mapper.toCliente(entity)).thenReturn(cliente);

        List<Cliente> result = clienteGateway.buscarClientePorEmailECpf(email, cpf);

        assertThat(result).containsExactly(cliente);
        verify(clienteDatabase).buscarClientePorEmailECpf(email.getEndereco(), cpf.getValue());
        verify(mapper).toCliente(entity);
    }
}
