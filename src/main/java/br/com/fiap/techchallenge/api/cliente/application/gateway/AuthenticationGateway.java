package br.com.fiap.techchallenge.api.cliente.application.gateway;

import br.com.fiap.techchallenge.api.cliente.domain.Cliente;

public interface AuthenticationGateway {
    void cadastrarCliente(Cliente cliente);
    boolean isClienteExistente(Cliente cliente);
}
