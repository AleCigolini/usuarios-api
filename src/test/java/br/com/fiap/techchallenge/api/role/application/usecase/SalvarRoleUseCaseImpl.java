package br.com.fiap.techchallenge.api.role.application.usecase;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.techchallenge.api.core.config.exception.exceptions.NegocioException;
import br.com.fiap.techchallenge.api.role.application.gateway.RoleGateway;
import br.com.fiap.techchallenge.api.role.application.usecase.impl.SalvarRoleUseCaseImpl;
import br.com.fiap.techchallenge.api.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SalvarRoleUseCaseImplTest {

    @Mock
    private RoleGateway roleGateway;

    private SalvarRoleUseCaseImpl salvarRoleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        salvarRoleUseCase = new SalvarRoleUseCaseImpl(roleGateway);
    }

    @Test
    void deveSalvarRoleQuandoNaoExiste() {
        Role role = new Role();
        role.setRole("ADMIN");
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(null);
        when(roleGateway.salvarRole(any(Role.class))).thenReturn(role);

        Role resultado = salvarRoleUseCase.salvarRole(role);

        assertNotNull(resultado);
        assertEquals(role, resultado);
        assertTrue(resultado.getAtivo());
        verify(roleGateway).buscarRolePorNome("ADMIN");
        verify(roleGateway).salvarRole(role);
    }

    @Test
    void deveLancarExcecaoQuandoRoleJaExiste() {
        Role role = new Role();
        role.setRole("ADMIN");
        Role existente = new Role();
        existente.setRole("ADMIN");
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(existente);

        NegocioException ex = assertThrows(NegocioException.class, () -> salvarRoleUseCase.salvarRole(role));
        assertTrue(ex.getMessage().contains("Role já existe"));
        verify(roleGateway).buscarRolePorNome("ADMIN");
        verify(roleGateway, never()).salvarRole(any());
    }

    @Test
    void deveHabilitarRolePorNomeQuandoInativa() {
        Role role = new Role();
        role.setRole("ADMIN");
        role.setAtivo(false);
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(role);
        when(roleGateway.salvarRole(any(Role.class))).thenReturn(role);

        Role resultado = salvarRoleUseCase.habilitarRolePorNome("ADMIN");

        assertNotNull(resultado);
        assertTrue(resultado.getAtivo());
        verify(roleGateway).buscarRolePorNome("ADMIN");
        verify(roleGateway).salvarRole(role);
    }

    @Test
    void deveRetornarRoleJaAtivaAoHabilitar() {
        Role role = new Role();
        role.setRole("ADMIN");
        role.setAtivo(true);
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(role);

        Role resultado = salvarRoleUseCase.habilitarRolePorNome("ADMIN");

        assertNotNull(resultado);
        assertTrue(resultado.getAtivo());
        verify(roleGateway).buscarRolePorNome("ADMIN");
        verify(roleGateway, never()).salvarRole(any());
    }

    @Test
    void deveLancarExcecaoAoHabilitarRoleNaoEncontrada() {
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(null);

        assertThrows(EntidadeNaoEncontradaException.class, () -> salvarRoleUseCase.habilitarRolePorNome("ADMIN"));
        verify(roleGateway).buscarRolePorNome("ADMIN");
    }

    @Test
    void deveExcluirRolePorNomeQuandoAtiva() {
        Role role = new Role();
        role.setRole("ADMIN");
        role.setAtivo(true);
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(role);
        when(roleGateway.salvarRole(any(Role.class))).thenReturn(role);

        salvarRoleUseCase.excluirRolePorNome("ADMIN");

        assertFalse(role.getAtivo());
        verify(roleGateway).buscarRolePorNome("ADMIN");
        verify(roleGateway).salvarRole(role);
    }

    @Test
    void deveLancarExcecaoAoExcluirRoleJaInativa() {
        Role role = new Role();
        role.setRole("ADMIN");
        role.setAtivo(false);
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(role);

        NegocioException ex = assertThrows(NegocioException.class, () -> salvarRoleUseCase.excluirRolePorNome("ADMIN"));
        assertTrue(ex.getMessage().contains("Role já está inativa"));
        verify(roleGateway).buscarRolePorNome("ADMIN");
        verify(roleGateway, never()).salvarRole(any());
    }

    @Test
    void deveLancarExcecaoAoExcluirRoleNaoEncontrada() {
        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(null);

        assertThrows(EntidadeNaoEncontradaException.class, () -> salvarRoleUseCase.excluirRolePorNome("ADMIN"));
        verify(roleGateway).buscarRolePorNome("ADMIN");
    }
}
