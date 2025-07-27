package br.com.fiap.techchallenge.api.role.application.gateway.impl;

import br.com.fiap.techchallenge.api.role.application.gateway.RoleGateway;
import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.role.common.interfaces.RoleDatabase;
import br.com.fiap.techchallenge.api.role.domain.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleGatewayImpl implements RoleGateway {
    private RoleDatabase roleDatabase;
    private DatabaseRoleMapper mapper;


    @Override
    public Role salvarRole(Role role) {
        return mapper.toRole(roleDatabase.salvarRole(mapper.toJpaRoleEntity(role)));
    }

    @Override
    public Role buscarRolePorNome(String role) {
        final Optional<JpaRoleEntity> optionalRole = roleDatabase.buscarRolePorNome(role);
        return optionalRole.map(value -> mapper.toRole(value)).orElse(null);
    }

    @Override
    public List<Role> buscarRoles() {
        return roleDatabase.buscarRoles()
                .stream()
                .map(mapper::toRole)
                .toList();
    }
}
