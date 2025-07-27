package br.com.fiap.techchallenge.api.role.application.usecase;

import br.com.fiap.techchallenge.api.role.domain.Role;

public interface SalvarRoleUseCase {
    Role salvarRole(Role role);
    Role habilitarRolePorNome(String role);
    void excluirRolePorNome(String role);
}
