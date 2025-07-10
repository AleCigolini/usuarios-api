package br.com.fiap.techchallenge03.cliente.application.usecase.impl;

import br.com.fiap.techchallenge03.cliente.application.gateway.ClienteGateway;
import br.com.fiap.techchallenge03.cliente.application.usecase.ConsultarClienteUseCase;
import br.com.fiap.techchallenge03.cliente.common.domain.exception.ClienteNaoEncontradoException;
import br.com.fiap.techchallenge03.cliente.common.domain.exception.ClienteValidacaoException;
import br.com.fiap.techchallenge03.cliente.domain.Cliente;
import br.com.fiap.techchallenge03.core.utils.domain.Cpf;
import br.com.fiap.techchallenge03.core.utils.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {
    private ClienteGateway clienteGateway;

    @Override
    public Cliente buscarClientePorCpf(Cpf cpf) {
        List<Cliente> usuariosEncontradosPorCpf = clienteGateway.buscarClientePorCpf(cpf);
        validarListaClienteUnicoEncontrado(usuariosEncontradosPorCpf, "cpf", cpf.getValue());
        return usuariosEncontradosPorCpf.getFirst();
    }

    @Override
    public Cliente buscarClientePorId(UUID id) {
        Cliente cliente = clienteGateway.buscarClientePorId(id)
                .orElse(null);
        if (cliente == null) {
            this.throwClienteNaoEncontradoException("id", id.toString());
        }
        return cliente;
    }

    @Override
    public Cliente buscarClientePorEmail(Email email) {
        List<Cliente> usuariosEncontradosPorEmail = clienteGateway.buscarClientePorEmail(email);
        validarListaClienteUnicoEncontrado(usuariosEncontradosPorEmail, "email", email.getEndereco());
        return usuariosEncontradosPorEmail.getFirst();
    }

    @Override
    public Cliente buscarClientePorEmailCpf(Cpf cpf, Email email, Boolean throwExceptionWhenNotFound) {
        List<Cliente> clientes = buscaClientesEmailCpf(cpf, email);
        Boolean isClientesEmpty = clientes.isEmpty();
        if (isClientesEmpty && throwExceptionWhenNotFound) {
            throw new ClienteNaoEncontradoException("Não foi encontrado nenhum cliente");
        }
        return isClientesEmpty ? null : clientes.getFirst();
    }

    private List<Cliente> buscaClientesEmailCpf(Cpf cpf, Email email) {
        if (cpf == null && email == null) {
            throw new ClienteValidacaoException("Pelo menos um dos campos (cpf ou email) deve ser informado.");
        }

        if (cpf != null && email != null) {
            return clienteGateway.buscarClientePorEmailECpf(email, cpf);
        }
        if (cpf != null) {
            return clienteGateway.buscarClientePorCpf(cpf);
        }
        return clienteGateway.buscarClientePorEmail(email);
    }

    private void validarListaClienteUnicoEncontrado(List<Cliente> clientes, String campoBusca, String valorBusca) {
        if (clientes.isEmpty()) {
            this.throwClienteNaoEncontradoException(campoBusca, valorBusca);
        }
        if (clientes.size() > 1) {
            throw new ClienteValidacaoException(String.format("Encontrado mais de um cliente para o %s: %s", campoBusca, valorBusca));
        }
    }

    private void throwClienteNaoEncontradoException(String campoBusca, String valorBusca) {
        throw new ClienteNaoEncontradoException(String.format("Não foi encontrado nenhum cliente para o %s: %s", campoBusca, valorBusca));
    }
}
