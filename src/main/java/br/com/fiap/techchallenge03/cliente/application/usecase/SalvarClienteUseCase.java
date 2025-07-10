package br.com.fiap.techchallenge03.cliente.application.usecase;

import br.com.fiap.techchallenge03.cliente.domain.Cliente;

public interface SalvarClienteUseCase {
    Cliente salvarCliente(Cliente clienteRequestDto);
}
