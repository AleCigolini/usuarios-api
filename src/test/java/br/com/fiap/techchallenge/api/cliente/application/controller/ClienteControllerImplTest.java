package br.com.fiap.techchallenge.api.cliente.application.controller;

import br.com.fiap.techchallenge.api.cliente.application.controller.impl.ClienteControllerImpl;
import br.com.fiap.techchallenge.api.cliente.application.gateway.impl.AuthenticationGatewayImpl;
import br.com.fiap.techchallenge.api.cliente.application.gateway.impl.ClienteGatewayImpl;
import br.com.fiap.techchallenge.api.cliente.application.mapper.DatabaseClienteMapper;
import br.com.fiap.techchallenge.api.cliente.application.mapper.RequestClienteMapper;
import br.com.fiap.techchallenge.api.cliente.application.presenter.ClientePresenter;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteControllerImplTest {

    private ClienteControllerImpl controller;

    private ClienteDatabase clienteDatabase;
    private DatabaseClienteMapper databaseClienteMapper;
    private RequestClienteMapper requestClienteMapper;
    private ClientePresenter clientePresenter;
    private ClienteAuthentication clienteAuthentication;

    @BeforeEach
    void setUp() {
        clienteDatabase = mock(ClienteDatabase.class);
        databaseClienteMapper = mock(DatabaseClienteMapper.class);
        requestClienteMapper = mock(RequestClienteMapper.class);
        clientePresenter = mock(ClientePresenter.class);
        clienteAuthentication = mock(ClienteAuthentication.class);
    }

    @Test
    void deveBuscarClientePorCpf() {
        try (
                MockedConstruction<ClienteGatewayImpl> mockedClienteGateway =
                        mockConstruction(ClienteGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarClienteUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarClienteUseCaseImpl.class);
                MockedConstruction<ConsultarClienteUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarClienteUseCaseImpl.class, (mock, context) -> {
                            Cliente cliente = new Cliente();
                            when(mock.buscarClientePorCpf(any())).thenReturn(cliente);
                        })
        ) {
            ClienteResponseDto responseEsperado = new ClienteResponseDto();
            when(clientePresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new ClienteControllerImpl(
                    clienteDatabase,
                    databaseClienteMapper,
                    requestClienteMapper,
                    clientePresenter,
                    clienteAuthentication
            );

            ClienteResponseDto response = controller.buscarClientePorCpf("12697331093");

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveCadastrarCliente() {
        try (
                MockedConstruction<ClienteGatewayImpl> mockedClienteGateway =
                        mockConstruction(ClienteGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarClienteUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarClienteUseCaseImpl.class, (mock, context) -> {
                            Cliente cliente = new Cliente();
                            when(mock.salvarCliente(any())).thenReturn(cliente);
                        });
                MockedConstruction<ConsultarClienteUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarClienteUseCaseImpl.class)
        ) {
            Cliente cliente = new Cliente();
            ClienteResponseDto responseEsperado = new ClienteResponseDto();

            when(requestClienteMapper.requestDtoToDomain(any(ClienteRequestDto.class))).thenReturn(cliente);
            when(clientePresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new ClienteControllerImpl(
                    clienteDatabase,
                    databaseClienteMapper,
                    requestClienteMapper,
                    clientePresenter,
                    clienteAuthentication
            );

            ClienteRequestDto dto = new ClienteRequestDto(); // Popule conforme necessário
            ClienteResponseDto response = controller.cadastrarCliente(dto);

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveBuscarClientePorEmail() {
        try (
                MockedConstruction<ClienteGatewayImpl> mockedClienteGateway =
                        mockConstruction(ClienteGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarClienteUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarClienteUseCaseImpl.class);
                MockedConstruction<ConsultarClienteUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarClienteUseCaseImpl.class, (mock, context) -> {
                            Cliente cliente = new Cliente();
                            when(mock.buscarClientePorEmail(any(Email.class))).thenReturn(cliente);
                        })
        ) {
            ClienteResponseDto responseEsperado = new ClienteResponseDto();
            when(clientePresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new ClienteControllerImpl(
                    clienteDatabase,
                    databaseClienteMapper,
                    requestClienteMapper,
                    clientePresenter,
                    clienteAuthentication
            );

            ClienteResponseDto response = controller.buscarClientePorEmail("email@teste.com");

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveBuscarClientePorId() {
        try (
                MockedConstruction<ClienteGatewayImpl> mockedClienteGateway =
                        mockConstruction(ClienteGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarClienteUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarClienteUseCaseImpl.class);
                MockedConstruction<ConsultarClienteUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarClienteUseCaseImpl.class, (mock, context) -> {
                            Cliente cliente = new Cliente();
                            when(mock.buscarClientePorId(any(UUID.class))).thenReturn(cliente);
                        })
        ) {
            ClienteResponseDto responseEsperado = new ClienteResponseDto();
            when(clientePresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new ClienteControllerImpl(
                    clienteDatabase,
                    databaseClienteMapper,
                    requestClienteMapper,
                    clientePresenter,
                    clienteAuthentication
            );

            ClienteResponseDto response = controller.buscarClientePorId(UUID.randomUUID());

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveIdentificarClienteExistente() {
        try (
                MockedConstruction<ClienteGatewayImpl> mockedClienteGateway =
                        mockConstruction(ClienteGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarClienteUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarClienteUseCaseImpl.class);
                MockedConstruction<ConsultarClienteUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarClienteUseCaseImpl.class, (mock, context) -> {
                            Cliente cliente = new Cliente();
                            when(mock.buscarClientePorEmailCpf(any(Cpf.class), any(Email.class), any())).thenReturn(cliente);
                        })
        ) {
            Cliente cliente = new Cliente();
            cliente.setCpf(new Cpf("12697331093"));
            cliente.setEmail(new Email("teste@email.com"));
            when(requestClienteMapper.requestDtoToDomain(any(IdentificarClienteDto.class)))
                    .thenReturn(cliente);
            ClienteResponseDto responseEsperado = new ClienteResponseDto();
            when(clientePresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new ClienteControllerImpl(
                    clienteDatabase,
                    databaseClienteMapper,
                    requestClienteMapper,
                    clientePresenter,
                    clienteAuthentication
            );

            ClienteResponseDto response = controller.identificarCliente(new IdentificarClienteDto());

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveIdentificarClienteNaoExistente() {
        try (
                MockedConstruction<ClienteGatewayImpl> mockedClienteGateway =
                        mockConstruction(ClienteGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarClienteUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarClienteUseCaseImpl.class);
                MockedConstruction<ConsultarClienteUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarClienteUseCaseImpl.class, (mock, context) -> {
                            Cliente cliente = new Cliente();
                            when(mock.buscarClientePorEmailCpf(any(Cpf.class), any(Email.class), any())).thenReturn(null);
                        })
        ) {
            Cliente cliente = new Cliente();
            cliente.setCpf(new Cpf("12697331093"));
            cliente.setEmail(new Email("teste@email.com"));
            when(requestClienteMapper.requestDtoToDomain(any(IdentificarClienteDto.class)))
                    .thenReturn(cliente);
            ClienteResponseDto responseEsperado = new ClienteResponseDto();
            when(clientePresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new ClienteControllerImpl(
                    clienteDatabase,
                    databaseClienteMapper,
                    requestClienteMapper,
                    clientePresenter,
                    clienteAuthentication
            );

            ClienteResponseDto response = controller.identificarCliente(new IdentificarClienteDto());

            assertEquals(responseEsperado, response);
        }
    }
}

