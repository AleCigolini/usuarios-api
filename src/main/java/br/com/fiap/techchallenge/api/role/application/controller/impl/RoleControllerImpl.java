package br.com.fiap.techchallenge.api.role.application.controller.impl;

import br.com.fiap.techchallenge.api.role.application.controller.RoleController;
import br.com.fiap.techchallenge.api.role.application.gateway.RoleGateway;
import br.com.fiap.techchallenge.api.role.application.gateway.impl.RoleGatewayImpl;
import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.role.application.mapper.RequestRoleMapper;
import br.com.fiap.techchallenge.api.role.application.presenter.RolePresenter;
import br.com.fiap.techchallenge.api.role.application.usecase.ConsultarRoleUseCase;
import br.com.fiap.techchallenge.api.role.application.usecase.SalvarRoleUseCase;
import br.com.fiap.techchallenge.api.role.application.usecase.impl.ConsultarRoleUseCaseImpl;
import br.com.fiap.techchallenge.api.role.application.usecase.impl.SalvarRoleUseCaseImpl;
import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import br.com.fiap.techchallenge.api.role.common.interfaces.RoleDatabase;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RoleControllerImpl implements RoleController {
    private final SalvarRoleUseCase salvarRoleUseCase;
    private final ConsultarRoleUseCase consultarRoleUseCase;

    private final RequestRoleMapper requestRoleMapper;
    private final RolePresenter rolePresenter;

    public RoleControllerImpl(RoleDatabase roleDatabase, DatabaseRoleMapper databaseRoleMapper, RequestRoleMapper requestRoleMapper, RolePresenter rolePresenter) {
        final RoleGateway roleGateway = new RoleGatewayImpl(roleDatabase, databaseRoleMapper);
        this.requestRoleMapper = requestRoleMapper;
        this.rolePresenter = rolePresenter;
        this.salvarRoleUseCase = new SalvarRoleUseCaseImpl(roleGateway);
        this.consultarRoleUseCase = new ConsultarRoleUseCaseImpl(roleGateway);
    }

    @Override
    public RoleResponseDto cadastrarRole(RoleRequestDto roleRequestDto) {
        return rolePresenter.toResponse(salvarRoleUseCase.salvarRole(requestRoleMapper.requestDtoToDomain(roleRequestDto)));
    }

    @Override
    public RoleResponseDto buscarRolePorNome(String role) {
        return rolePresenter.toResponse(consultarRoleUseCase.buscarRolePorNome(role));
    }

    @Override
    public RoleResponseDto habilitarRolePorNome(String role) {
        return rolePresenter.toResponse(salvarRoleUseCase.habilitarRolePorNome(role));
    }

    @Override
    public void excluirRolePorNome(String role) {
        salvarRoleUseCase.excluirRolePorNome(role);
    }

    @Override
    public List<RoleResponseDto> consultarRoles() {
        return consultarRoleUseCase.consultarRoles().stream()
                .map(rolePresenter::toResponse)
                .toList();
    }
}
