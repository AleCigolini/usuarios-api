package br.com.fiap.techchallenge.api.role.application.mapper;

import br.com.fiap.techchallenge.api.role.application.mapper.modelmapper.DatabaseRoleModelMapper;
import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DatabaseRoleModelMapperTest {
    private ModelMapper modelMapper;
    private DatabaseRoleModelMapper databaseRoleMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        databaseRoleMapper = new DatabaseRoleModelMapper(modelMapper);
    }

    @Test
    void deveMapearParaJpaRoleEntity() {
        Role role = new Role();
        JpaRoleEntity entity = new JpaRoleEntity();
        when(modelMapper.map(role, JpaRoleEntity.class)).thenReturn(entity);

        JpaRoleEntity result = databaseRoleMapper.toJpaRoleEntity(role);

        assertThat(result).isEqualTo(entity);
        verify(modelMapper).map(role, JpaRoleEntity.class);
    }

    @Test
    void deveMapearParaRole() {
        JpaRoleEntity entity = new JpaRoleEntity();
        Role role = new Role();
        when(modelMapper.map(entity, Role.class)).thenReturn(role);

        Role result = databaseRoleMapper.toRole(entity);

        assertThat(result).isEqualTo(role);
        verify(modelMapper).map(entity, Role.class);
    }
}
