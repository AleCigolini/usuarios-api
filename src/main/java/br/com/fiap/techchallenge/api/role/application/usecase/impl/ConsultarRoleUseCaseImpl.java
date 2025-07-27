package br.com.fiap.techchallenge.api.role.application.usecase.impl;

import br.com.fiap.techchallenge.api.role.application.gateway.RoleGateway;
import br.com.fiap.techchallenge.api.role.application.usecase.ConsultarRoleUseCase;
import br.com.fiap.techchallenge.api.role.domain.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsultarRoleUseCaseImpl implements ConsultarRoleUseCase {
    private RoleGateway roleGateway;

    @Override
    public List<Role> consultarRoles() {
        return roleGateway.buscarRoles();
    }

    @Override
    public Role buscarRolePorNome(String role) {
        return roleGateway.buscarRolePorNome(role.toUpperCase());
    }
}
