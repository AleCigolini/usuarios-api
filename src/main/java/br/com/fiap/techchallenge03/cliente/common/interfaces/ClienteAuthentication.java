package br.com.fiap.techchallenge03.cliente.common.interfaces;

import br.com.fiap.techchallenge03.cliente.domain.Cliente;

public interface ClienteAuthentication {
    void salvarClienteAuthentication(Cliente cliente);
    boolean isClienteExistente(Cliente cliente);
}
