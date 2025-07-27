package br.com.fiap.techchallenge.api.role.application.usecase.impl;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.techchallenge.api.core.config.exception.exceptions.NegocioException;
import br.com.fiap.techchallenge.api.role.application.gateway.RoleGateway;
import br.com.fiap.techchallenge.api.role.application.usecase.SalvarRoleUseCase;
import br.com.fiap.techchallenge.api.role.domain.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalvarRoleUseCaseImpl implements SalvarRoleUseCase {
    private RoleGateway roleGateway;

    @Override
    public Role salvarRole(Role role) {
        Role roleExistente = roleGateway.buscarRolePorNome(role.getRole());
        if (roleExistente != null) {
            throw new NegocioException("Role já existe: " + role.getRole());
        }
        tratarRoleAntesDeSalvar(role);
        return roleGateway.salvarRole(role);
    }

    @Override
    public Role habilitarRolePorNome(String role) {
        Role roleExistente = buscarRolePorNome(role.toUpperCase());

        if(!roleExistente.getAtivo()) {
            roleExistente.setAtivo(true);
            return roleGateway.salvarRole(roleExistente);
        }

        return roleExistente;
    }

    @Override
    public void excluirRolePorNome(String role) {
        Role roleExistente = buscarRolePorNome(role.toUpperCase());
        if (!roleExistente.getAtivo()) {
            throw new NegocioException("Role já está inativa: " + role);
        }
        roleExistente.setAtivo(false);
        roleGateway.salvarRole(roleExistente);
    }

    private void tratarRoleAntesDeSalvar(Role role) {
        role.setRole(role.getRole().toUpperCase());
        role.setAtivo(true);
    }

    private Role buscarRolePorNome(String role) {
        Role roleExistente = roleGateway.buscarRolePorNome(role);
        if (roleExistente == null) {
            throw new EntidadeNaoEncontradaException("Role não encontrada: " + role);
        }
        return roleExistente;
    }
}
