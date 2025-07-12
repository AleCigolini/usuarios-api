package br.com.fiap.techchallenge.api.cliente.application.usecase;

import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;

import java.util.UUID;

public interface ConsultarClienteUseCase {
    Cliente buscarClientePorCpf(Cpf cpf);

    Cliente buscarClientePorId(UUID id);

    Cliente buscarClientePorEmail(Email email);

    Cliente buscarClientePorEmailCpf(Cpf cpf, Email email, Boolean throwExceptionWhenNotFound);
}
