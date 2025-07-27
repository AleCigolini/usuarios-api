package br.com.fiap.techchallenge.api.role.application.mapper;

import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.role.domain.Role;

public interface DatabaseRoleMapper {
    JpaRoleEntity toJpaRoleEntity(Role role);
    Role toRole(JpaRoleEntity jpaRoleEntity);
}