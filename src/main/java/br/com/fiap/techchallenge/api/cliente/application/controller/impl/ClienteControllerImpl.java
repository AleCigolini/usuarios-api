package br.com.fiap.techchallenge.api.cliente.application.controller.impl;

import br.com.fiap.techchallenge.api.cliente.application.controller.ClienteController;
import br.com.fiap.techchallenge.api.cliente.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge.api.cliente.application.gateway.ClienteGateway;
import br.com.fiap.techchallenge.api.cliente.application.gateway.impl.AuthenticationGatewayImpl;
import br.com.fiap.techchallenge.api.cliente.application.gateway.impl.ClienteGatewayImpl;
import br.com.fiap.techchallenge.api.cliente.application.mapper.DatabaseClienteMapper;
import br.com.fiap.techchallenge.api.cliente.application.mapper.RequestClienteMapper;
import br.com.fiap.techchallenge.api.cliente.application.presenter.ClientePresenter;
import br.com.fiap.techchallenge.api.cliente.application.usecase.ConsultarClienteUseCase;
import br.com.fiap.techchallenge.api.cliente.application.usecase.SalvarClienteUseCase;
import br.com.fiap.techchallenge.api.cliente.application.usecase.impl.ConsultarClienteUseCaseImpl;
import br.com.fiap.techchallenge.api.cliente.application.usecase.impl.SalvarClienteUseCaseImpl;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.response.ClienteResponseDto;
import br.com.fiap.techchallenge.api.cliente.common.interfaces.ClienteAuthentication;
import br.com.fiap.techchallenge.api.cliente.common.interfaces.ClienteDatabase;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ClienteControllerImpl implements ClienteController {
    private final SalvarClienteUseCase salvarClienteUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;

    private final RequestClienteMapper requestClienteMapper;
    private final ClientePresenter clientePresenter;

    public ClienteControllerImpl(ClienteDatabase clienteDatabase, DatabaseClienteMapper mapper, RequestClienteMapper requestClienteMapper, ClientePresenter clientePresenter, ClienteAuthentication clienteAuthentication) {
        this.requestClienteMapper = requestClienteMapper;
        this.clientePresenter = clientePresenter;
        final ClienteGateway clienteGateway = new ClienteGatewayImpl(clienteDatabase, mapper);
        final AuthenticationGateway authenticationGateway = new AuthenticationGatewayImpl(clienteAuthentication);
        this.salvarClienteUseCase = new SalvarClienteUseCaseImpl(clienteGateway, authenticationGateway);
        this.consultarClienteUseCase = new ConsultarClienteUseCaseImpl(clienteGateway);
    }

    @Override
    public ClienteResponseDto buscarClientePorCpf(String cpf) {
        return clientePresenter.toResponse(consultarClienteUseCase.buscarClientePorCpf(new Cpf(cpf)));
    }

    @Override
    public ClienteResponseDto buscarClientePorEmail(String email) {
        return clientePresenter.toResponse(consultarClienteUseCase.buscarClientePorEmail(new Email(email)));
    }

    @Override
    public ClienteResponseDto buscarClientePorId(UUID id) {
        return clientePresenter.toResponse(consultarClienteUseCase.buscarClientePorId(id));
    }

    @Override
    public ClienteResponseDto cadastrarCliente(ClienteRequestDto clienteRequestDto) {
        return clientePresenter.toResponse(salvarClienteUseCase.salvarCliente(requestClienteMapper.requestDtoToDomain(clienteRequestDto)));
    }

    @Override
    public ClienteResponseDto identificarCliente(IdentificarClienteDto identificarClienteDto) {
        Cliente clienteParametros = requestClienteMapper.requestDtoToDomain(identificarClienteDto);
        Cliente clienteExistente = consultarClienteUseCase.buscarClientePorEmailCpf(clienteParametros.getCpf(), clienteParametros.getEmail(), false);
        if (clienteExistente != null) {
            return clientePresenter.toResponse(clienteExistente);
        }
        return clientePresenter.toResponse(salvarClienteUseCase.salvarCliente(clienteParametros));
    }
}
