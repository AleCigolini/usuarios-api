package br.com.fiap.techchallenge.api.cliente.application.gateway.impl;

import br.com.fiap.techchallenge.api.cliente.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge.api.cliente.common.interfaces.ClienteAuthentication;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationGatewayImpl implements AuthenticationGateway {
    private final ClienteAuthentication clienteAuthentication;

    @Override
    public void cadastrarCliente(Cliente cliente) {
        clienteAuthentication.salvarClienteAuthentication(cliente);
    }

    @Override
    public boolean isClienteExistente(Cliente cliente) {
        return clienteAuthentication.isClienteExistente(cliente);
    }
}
