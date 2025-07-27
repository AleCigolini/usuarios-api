package br.com.fiap.techchallenge.api.role.application.usecase;

import br.com.fiap.techchallenge.api.role.domain.Role;

import java.util.List;

public interface ConsultarRoleUseCase {
    List<Role> consultarRoles();
    Role buscarRolePorNome(String role);
}
