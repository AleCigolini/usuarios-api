package br.com.fiap.techchallenge.api.usuario.application.controller;

import br.com.fiap.techchallenge.api.usuario.application.controller.impl.UsuarioControllerImpl;
import br.com.fiap.techchallenge.api.usuario.application.gateway.impl.AuthenticationGatewayImpl;
import br.com.fiap.techchallenge.api.usuario.application.gateway.impl.UsuarioGatewayImpl;
import br.com.fiap.techchallenge.api.usuario.application.mapper.DatabaseUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.application.mapper.RequestUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.application.presenter.UsuarioPresenter;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.ConsultarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.SalvarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioAuthentication;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioDatabase;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioControllerImplTest {

    private UsuarioControllerImpl controller;

    private UsuarioDatabase usuarioDatabase;
    private DatabaseUsuarioMapper databaseUsuarioMapper;
    private RequestUsuarioMapper requestUsuarioMapper;
    private UsuarioPresenter usuarioPresenter;
    private UsuarioAuthentication usuarioAuthentication;

    @BeforeEach
    void setUp() {
        usuarioDatabase = mock(UsuarioDatabase.class);
        databaseUsuarioMapper = mock(DatabaseUsuarioMapper.class);
        requestUsuarioMapper = mock(RequestUsuarioMapper.class);
        usuarioPresenter = mock(UsuarioPresenter.class);
        usuarioAuthentication = mock(UsuarioAuthentication.class);
    }

    @Test
    void deveBuscarUsuarioPorCpf() {
        try (
                MockedConstruction<UsuarioGatewayImpl> mockedUsuarioGateway =
                        mockConstruction(UsuarioGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarUsuarioUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarUsuarioUseCaseImpl.class);
                MockedConstruction<ConsultarUsuarioUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarUsuarioUseCaseImpl.class, (mock, context) -> {
                            Usuario usuario = new Usuario();
                            when(mock.buscarUsuarioPorCpf(any())).thenReturn(usuario);
                        })
        ) {
            UsuarioResponseDto responseEsperado = new UsuarioResponseDto();
            when(usuarioPresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new UsuarioControllerImpl(
                    usuarioDatabase,
                    databaseUsuarioMapper,
                    requestUsuarioMapper,
                    usuarioPresenter,
                    usuarioAuthentication
            );

            UsuarioResponseDto response = controller.buscarUsuarioPorCpf("12697331093");

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveCadastrarUsuario() {
        try (
                MockedConstruction<UsuarioGatewayImpl> mockedUsuarioGateway =
                        mockConstruction(UsuarioGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarUsuarioUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarUsuarioUseCaseImpl.class, (mock, context) -> {
                            Usuario usuario = new Usuario();
                            when(mock.salvarUsuario(any())).thenReturn(usuario);
                        });
                MockedConstruction<ConsultarUsuarioUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarUsuarioUseCaseImpl.class)
        ) {
            Usuario usuario = new Usuario();
            UsuarioResponseDto responseEsperado = new UsuarioResponseDto();

            when(requestUsuarioMapper.requestDtoToDomain(any(UsuarioRequestDto.class))).thenReturn(usuario);
            when(usuarioPresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new UsuarioControllerImpl(
                    usuarioDatabase,
                    databaseUsuarioMapper,
                    requestUsuarioMapper,
                    usuarioPresenter,
                    usuarioAuthentication
            );

            UsuarioRequestDto dto = new UsuarioRequestDto(); // Popule conforme necessário
            UsuarioResponseDto response = controller.cadastrarUsuario(dto);

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        try (
                MockedConstruction<UsuarioGatewayImpl> mockedUsuarioGateway =
                        mockConstruction(UsuarioGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarUsuarioUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarUsuarioUseCaseImpl.class);
                MockedConstruction<ConsultarUsuarioUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarUsuarioUseCaseImpl.class, (mock, context) -> {
                            Usuario usuario = new Usuario();
                            when(mock.buscarUsuarioPorEmail(any(Email.class))).thenReturn(usuario);
                        })
        ) {
            UsuarioResponseDto responseEsperado = new UsuarioResponseDto();
            when(usuarioPresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new UsuarioControllerImpl(
                    usuarioDatabase,
                    databaseUsuarioMapper,
                    requestUsuarioMapper,
                    usuarioPresenter,
                    usuarioAuthentication
            );

            UsuarioResponseDto response = controller.buscarUsuarioPorEmail("email@teste.com");

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveBuscarUsuarioPorId() {
        try (
                MockedConstruction<UsuarioGatewayImpl> mockedUsuarioGateway =
                        mockConstruction(UsuarioGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarUsuarioUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarUsuarioUseCaseImpl.class);
                MockedConstruction<ConsultarUsuarioUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarUsuarioUseCaseImpl.class, (mock, context) -> {
                            Usuario usuario = new Usuario();
                            when(mock.buscarUsuarioPorId(any(UUID.class))).thenReturn(usuario);
                        })
        ) {
            UsuarioResponseDto responseEsperado = new UsuarioResponseDto();
            when(usuarioPresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new UsuarioControllerImpl(
                    usuarioDatabase,
                    databaseUsuarioMapper,
                    requestUsuarioMapper,
                    usuarioPresenter,
                    usuarioAuthentication
            );

            UsuarioResponseDto response = controller.buscarUsuarioPorId(UUID.randomUUID());

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveIdentificarUsuarioExistente() {
        try (
                MockedConstruction<UsuarioGatewayImpl> mockedUsuarioGateway =
                        mockConstruction(UsuarioGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarUsuarioUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarUsuarioUseCaseImpl.class);
                MockedConstruction<ConsultarUsuarioUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarUsuarioUseCaseImpl.class, (mock, context) -> {
                            Usuario usuario = new Usuario();
                            when(mock.buscarUsuarioPorEmailCpf(any(Cpf.class), any(Email.class), any())).thenReturn(usuario);
                        })
        ) {
            Usuario usuario = new Usuario();
            usuario.setCpf(new Cpf("12697331093"));
            usuario.setEmail(new Email("teste@email.com"));
            when(requestUsuarioMapper.requestDtoToDomain(any(IdentificarUsuarioDto.class)))
                    .thenReturn(usuario);
            UsuarioResponseDto responseEsperado = new UsuarioResponseDto();
            when(usuarioPresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new UsuarioControllerImpl(
                    usuarioDatabase,
                    databaseUsuarioMapper,
                    requestUsuarioMapper,
                    usuarioPresenter,
                    usuarioAuthentication
            );

            UsuarioResponseDto response = controller.identificarUsuario(new IdentificarUsuarioDto());

            assertEquals(responseEsperado, response);
        }
    }

    @Test
    void deveIdentificarUsuarioNaoExistente() {
        try (
                MockedConstruction<UsuarioGatewayImpl> mockedUsuarioGateway =
                        mockConstruction(UsuarioGatewayImpl.class);
                MockedConstruction<AuthenticationGatewayImpl> mockedAuthGateway =
                        mockConstruction(AuthenticationGatewayImpl.class);
                MockedConstruction<SalvarUsuarioUseCaseImpl> mockedSalvarUseCase =
                        mockConstruction(SalvarUsuarioUseCaseImpl.class);
                MockedConstruction<ConsultarUsuarioUseCaseImpl> mockedConsultarUseCase =
                        mockConstruction(ConsultarUsuarioUseCaseImpl.class, (mock, context) -> {
                            Usuario usuario = new Usuario();
                            when(mock.buscarUsuarioPorEmailCpf(any(Cpf.class), any(Email.class), any())).thenReturn(null);
                        })
        ) {
            Usuario usuario = new Usuario();
            usuario.setCpf(new Cpf("12697331093"));
            usuario.setEmail(new Email("teste@email.com"));
            when(requestUsuarioMapper.requestDtoToDomain(any(IdentificarUsuarioDto.class)))
                    .thenReturn(usuario);
            UsuarioResponseDto responseEsperado = new UsuarioResponseDto();
            when(usuarioPresenter.toResponse(any())).thenReturn(responseEsperado);

            controller = new UsuarioControllerImpl(
                    usuarioDatabase,
                    databaseUsuarioMapper,
                    requestUsuarioMapper,
                    usuarioPresenter,
                    usuarioAuthentication
            );

            UsuarioResponseDto response = controller.identificarUsuario(new IdentificarUsuarioDto());

            assertEquals(responseEsperado, response);
        }
    }
}

