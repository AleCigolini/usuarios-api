package br.com.fiap.techchallenge.api.cliente.application.gateway.impl;

import br.com.fiap.techchallenge.api.cliente.application.gateway.ClienteGateway;
import br.com.fiap.techchallenge.api.cliente.application.mapper.DatabaseClienteMapper;
import br.com.fiap.techchallenge.api.cliente.common.interfaces.ClienteDatabase;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {
    private ClienteDatabase clienteDatabase;
    private DatabaseClienteMapper mapper;

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        final var clienteEntity = mapper.toJpaClienteEntity(cliente);
        return mapper.toCliente(clienteDatabase.salvarCliente(clienteEntity));
    }

    @Override
    public List<Cliente> buscarClientePorCpf(Cpf cpf) {
        return clienteDatabase.buscarClientePorCpf(cpf.getValue()).stream()
                .map(jpaClienteEntity -> mapper.toCliente(jpaClienteEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> buscarClientePorId(UUID id) {
        return clienteDatabase.buscarClientePorId(id).map(jpaClienteEntity -> mapper.toCliente(jpaClienteEntity));
    }

    @Override
    public List<Cliente> buscarClientePorEmail(Email email) {
        return clienteDatabase.buscarClientePorEmail(email.getEndereco()).stream()
                .map(jpaClienteEntity -> mapper.toCliente(jpaClienteEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> buscarClientePorEmailECpf(Email email, Cpf cpf) {
        return clienteDatabase.buscarClientePorEmailECpf(email.getEndereco(), cpf.getValue()).stream()
                .map(jpaClienteEntity -> mapper.toCliente(jpaClienteEntity))
                .collect(Collectors.toList());
    }
}
