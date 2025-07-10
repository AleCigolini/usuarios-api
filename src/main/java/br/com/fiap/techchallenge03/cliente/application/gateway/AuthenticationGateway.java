package br.com.fiap.techchallenge03.cliente.application.gateway;

import br.com.fiap.techchallenge03.cliente.domain.Cliente;

public interface AuthenticationGateway {
    void cadastrarCliente(Cliente cliente);
    boolean isClienteExistente(Cliente cliente);
}
