package br.com.fiap.techchallenge.api.role.application.controller;

import br.com.fiap.techchallenge.api.role.application.controller.impl.RoleControllerImpl;
import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.role.application.mapper.RequestRoleMapper;
import br.com.fiap.techchallenge.api.role.application.presenter.RolePresenter;
import br.com.fiap.techchallenge.api.role.application.usecase.impl.ConsultarRoleUseCaseImpl;
import br.com.fiap.techchallenge.api.role.application.usecase.impl.SalvarRoleUseCaseImpl;
import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import br.com.fiap.techchallenge.api.role.common.interfaces.RoleDatabase;
import br.com.fiap.techchallenge.api.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleControllerImplTest {


    @Mock
    private RequestRoleMapper requestRoleMapper;
    @Mock
    private RolePresenter rolePresenter;
    @Mock
    private RoleDatabase roleDatabase;
    @Mock
    private DatabaseRoleMapper databaseRoleMapper;

    private RoleControllerImpl roleController;
    private RoleRequestDto roleRequestDto;
    private RoleResponseDto roleResponseDto;
    private Role role;

    @BeforeEach
    void setUp() {
        roleRequestDto = new RoleRequestDto();
        role = new Role();
        role.setRole("ADMIN"); // Evita NullPointerException
        roleResponseDto = new RoleResponseDto();
    }

    @Test
    void deveCadastrarRole() {
        try (MockedConstruction<SalvarRoleUseCaseImpl> salvarMock = mockConstruction(SalvarRoleUseCaseImpl.class,
                (mock, context) -> when(mock.salvarRole(any())).thenReturn(role));
             MockedConstruction<ConsultarRoleUseCaseImpl> consultarMock = mockConstruction(ConsultarRoleUseCaseImpl.class)) {

            roleController = new RoleControllerImpl(roleDatabase, databaseRoleMapper, requestRoleMapper, rolePresenter);

            when(requestRoleMapper.requestDtoToDomain(any())).thenReturn(role);
            when(rolePresenter.toResponse(any())).thenReturn(roleResponseDto);

            RoleResponseDto resultado = roleController.cadastrarRole(roleRequestDto);

            assertNotNull(resultado);
            assertEquals(roleResponseDto, resultado);
        }
    }

    @Test
    void deveBuscarRolePorNome() {
        try (MockedConstruction<SalvarRoleUseCaseImpl> salvarMock = mockConstruction(SalvarRoleUseCaseImpl.class);
             MockedConstruction<ConsultarRoleUseCaseImpl> consultarMock = mockConstruction(ConsultarRoleUseCaseImpl.class,
                     (mock, context) -> when(mock.buscarRolePorNome(any())).thenReturn(role))) {

            roleController = new RoleControllerImpl(roleDatabase, databaseRoleMapper, requestRoleMapper, rolePresenter);

            when(rolePresenter.toResponse(any())).thenReturn(roleResponseDto);

            RoleResponseDto resultado = roleController.buscarRolePorNome("ADMIN");

            assertNotNull(resultado);
            assertEquals(roleResponseDto, resultado);
        }
    }

    @Test
    void deveHabilitarRolePorNome() {
        try (MockedConstruction<SalvarRoleUseCaseImpl> salvarMock = mockConstruction(SalvarRoleUseCaseImpl.class,
                (mock, context) -> when(mock.habilitarRolePorNome(any())).thenReturn(role));
             MockedConstruction<ConsultarRoleUseCaseImpl> consultarMock = mockConstruction(ConsultarRoleUseCaseImpl.class)) {

            roleController = new RoleControllerImpl(roleDatabase, databaseRoleMapper, requestRoleMapper, rolePresenter);

            when(rolePresenter.toResponse(any())).thenReturn(roleResponseDto);

            RoleResponseDto resultado = roleController.habilitarRolePorNome("ADMIN");

            assertNotNull(resultado);
            assertEquals(roleResponseDto, resultado);
        }
    }

    @Test
    void deveExcluirRolePorNome() {
        try (MockedConstruction<SalvarRoleUseCaseImpl> salvarMock = mockConstruction(SalvarRoleUseCaseImpl.class,
                (mock, context) -> {
                });
             MockedConstruction<ConsultarRoleUseCaseImpl> consultarMock = mockConstruction(ConsultarRoleUseCaseImpl.class)) {

            roleController = new RoleControllerImpl(roleDatabase, databaseRoleMapper, requestRoleMapper, rolePresenter);

            roleController.excluirRolePorNome("ADMIN");
        }
    }

    @Test
    void deveConsultarTodasRoles() {
        List<Role> roles = List.of(new Role(), new Role());
        List<RoleResponseDto> responseDtos = List.of(new RoleResponseDto(), new RoleResponseDto());

        try (MockedConstruction<SalvarRoleUseCaseImpl> salvarMock = mockConstruction(SalvarRoleUseCaseImpl.class);
             MockedConstruction<ConsultarRoleUseCaseImpl> consultarMock = mockConstruction(ConsultarRoleUseCaseImpl.class,
                     (mock, context) -> when(mock.consultarRoles()).thenReturn(roles))) {

            roleController = new RoleControllerImpl(roleDatabase, databaseRoleMapper, requestRoleMapper, rolePresenter);

            when(rolePresenter.toResponse(any(Role.class)))
                    .thenReturn(responseDtos.get(0), responseDtos.get(1));

            List<RoleResponseDto> resultado = roleController.consultarRoles();

            assertNotNull(resultado);
            assertEquals(2, resultado.size());
            assertEquals(responseDtos, resultado);
        }
    }
}