package br.com.fiap.techchallenge.api.cliente.application.usecase;

import br.com.fiap.techchallenge.api.cliente.domain.Cliente;

public interface SalvarClienteUseCase {
    Cliente salvarCliente(Cliente clienteRequestDto);
}
