package br.com.fiap.techchallenge03.cliente.application.gateway.impl;

import br.com.fiap.techchallenge03.cliente.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge03.cliente.common.interfaces.ClienteAuthentication;
import br.com.fiap.techchallenge03.cliente.domain.Cliente;
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
