package br.com.fiap.techchallenge.api.role.common.interfaces;

import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleDatabase {
    JpaRoleEntity salvarRole(JpaRoleEntity role);
    List<JpaRoleEntity> buscarRoles();
    Optional<JpaRoleEntity> buscarRolePorNome(String role);
}
