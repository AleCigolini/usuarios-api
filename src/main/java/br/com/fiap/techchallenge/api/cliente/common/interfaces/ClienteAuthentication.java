package br.com.fiap.techchallenge.api.cliente.common.interfaces;

import br.com.fiap.techchallenge.api.cliente.domain.Cliente;

public interface ClienteAuthentication {
    void salvarClienteAuthentication(Cliente cliente);
    boolean isClienteExistente(Cliente cliente);
}
