package br.com.fiap.techchallenge.api.role.application.gateway;

import br.com.fiap.techchallenge.api.role.domain.Role;

import java.util.List;

public interface RoleGateway {
    Role salvarRole(Role role);
    Role buscarRolePorNome(String role);
    List<Role> buscarRoles();;
}
