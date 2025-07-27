package br.com.fiap.techchallenge.api.role.infrastructure.database.repository;

import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.role.infrastructure.database.adapter.RoleJpaAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoleJpaAdapterTest {

    @Mock
    private JpaRoleRepository jpaRoleRepository;

    private RoleJpaAdapter roleJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleJpaAdapter = new RoleJpaAdapter(jpaRoleRepository);
    }

    @Test
    void deveSalvarRole() {
        JpaRoleEntity entity = new JpaRoleEntity();
        when(jpaRoleRepository.save(entity)).thenReturn(entity);

        JpaRoleEntity resultado = roleJpaAdapter.salvarRole(entity);

        assertNotNull(resultado);
        assertEquals(entity, resultado);
        verify(jpaRoleRepository).save(entity);
    }

    @Test
    void deveBuscarTodasRoles() {
        JpaRoleEntity entity1 = new JpaRoleEntity();
        JpaRoleEntity entity2 = new JpaRoleEntity();
        List<JpaRoleEntity> entities = List.of(entity1, entity2);
        when(jpaRoleRepository.findAll()).thenReturn(entities);

        List<JpaRoleEntity> resultado = roleJpaAdapter.buscarRoles();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(entity1));
        assertTrue(resultado.contains(entity2));
        verify(jpaRoleRepository).findAll();
    }

    @Test
    void deveBuscarRolePorNomeQuandoExiste() {
        String nome = "ADMIN";
        JpaRoleEntity entity = new JpaRoleEntity();
        when(jpaRoleRepository.findByRole(nome)).thenReturn(Optional.of(entity));

        Optional<JpaRoleEntity> resultado = roleJpaAdapter.buscarRolePorNome(nome);

        assertTrue(resultado.isPresent());
        assertEquals(entity, resultado.get());
        verify(jpaRoleRepository).findByRole(nome);
    }

    @Test
    void deveBuscarRolePorNomeQuandoNaoExiste() {
        String nome = "USER";
        when(jpaRoleRepository.findByRole(nome)).thenReturn(Optional.empty());

        Optional<JpaRoleEntity> resultado = roleJpaAdapter.buscarRolePorNome(nome);

        assertFalse(resultado.isPresent());
        verify(jpaRoleRepository).findByRole(nome);
    }
}
