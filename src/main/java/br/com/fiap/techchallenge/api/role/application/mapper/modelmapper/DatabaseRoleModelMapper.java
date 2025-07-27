package br.com.fiap.techchallenge.api.role.application.mapper.modelmapper;

import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.role.domain.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseRoleModelMapper implements DatabaseRoleMapper {
    private ModelMapper modelMapper;

    @Override
    public JpaRoleEntity toJpaRoleEntity(Role role) {
        return modelMapper.map(role, JpaRoleEntity.class);
    }

    @Override
    public Role toRole(JpaRoleEntity jpaRoleEntity) {
        return modelMapper.map(jpaRoleEntity, Role.class);
    }
}