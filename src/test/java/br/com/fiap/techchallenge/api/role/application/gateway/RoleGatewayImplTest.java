package br.com.fiap.techchallenge.api.role.application.gateway;

import br.com.fiap.techchallenge.api.role.application.gateway.impl.RoleGatewayImpl;
import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.role.common.interfaces.RoleDatabase;
import br.com.fiap.techchallenge.api.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RoleGatewayImplTest {

    @Mock
    private RoleDatabase roleDatabase;
    @Mock
    private DatabaseRoleMapper mapper;

    private RoleGatewayImpl roleGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleGateway = new RoleGatewayImpl(roleDatabase, mapper);
    }

    @Test
    void deveSalvarRole() {
        Role role = new Role();
        JpaRoleEntity entity = new JpaRoleEntity();
        when(mapper.toJpaRoleEntity(any(Role.class))).thenReturn(entity);
        when(roleDatabase.salvarRole(entity)).thenReturn(entity);
        when(mapper.toRole(entity)).thenReturn(role);

        Role resultado = roleGateway.salvarRole(role);

        assertNotNull(resultado);
        assertEquals(role, resultado);
        verify(mapper).toJpaRoleEntity(role);
        verify(roleDatabase).salvarRole(entity);
        verify(mapper).toRole(entity);
    }

    @Test
    void deveBuscarRolePorNomeQuandoExiste() {
        String nome = "ADMIN";
        JpaRoleEntity entity = new JpaRoleEntity();
        Role role = new Role();
        when(roleDatabase.buscarRolePorNome(nome)).thenReturn(Optional.of(entity));
        when(mapper.toRole(entity)).thenReturn(role);

        Role resultado = roleGateway.buscarRolePorNome(nome);

        assertNotNull(resultado);
        assertEquals(role, resultado);
        verify(roleDatabase).buscarRolePorNome(nome);
        verify(mapper).toRole(entity);
    }

    @Test
    void deveBuscarRolePorNomeQuandoNaoExiste() {
        String nome = "USER";
        when(roleDatabase.buscarRolePorNome(nome)).thenReturn(Optional.empty());

        Role resultado = roleGateway.buscarRolePorNome(nome);

        assertNull(resultado);
        verify(roleDatabase).buscarRolePorNome(nome);
        verifyNoInteractions(mapper);
    }

    @Test
    void deveBuscarTodasRoles() {
        JpaRoleEntity entity1 = new JpaRoleEntity();
        JpaRoleEntity entity2 = new JpaRoleEntity();
        Role role1 = new Role();
        Role role2 = new Role();
        List<JpaRoleEntity> entities = List.of(entity1, entity2);
        when(roleDatabase.buscarRoles()).thenReturn(entities);
        when(mapper.toRole(entity1)).thenReturn(role1);
        when(mapper.toRole(entity2)).thenReturn(role2);

        List<Role> resultado = roleGateway.buscarRoles();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(role1));
        assertTrue(resultado.contains(role2));
        verify(roleDatabase).buscarRoles();
    }

}
