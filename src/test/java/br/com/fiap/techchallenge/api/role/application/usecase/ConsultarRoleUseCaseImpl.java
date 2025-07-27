package br.com.fiap.techchallenge.api.role.application.usecase;

import br.com.fiap.techchallenge.api.role.application.gateway.RoleGateway;
import br.com.fiap.techchallenge.api.role.application.usecase.impl.ConsultarRoleUseCaseImpl;
import br.com.fiap.techchallenge.api.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsultarRoleUseCaseImplTest {

    @Mock
    private RoleGateway roleGateway;

    private ConsultarRoleUseCaseImpl consultarRoleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consultarRoleUseCase = new ConsultarRoleUseCaseImpl(roleGateway);
    }

    @Test
    void deveConsultarTodasRoles() {
        Role role1 = new Role();
        Role role2 = new Role();
        List<Role> roles = List.of(role1, role2);

        when(roleGateway.buscarRoles()).thenReturn(roles);

        List<Role> resultado = consultarRoleUseCase.consultarRoles();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(role1));
        assertTrue(resultado.contains(role2));
        verify(roleGateway).buscarRoles();
    }

    @Test
    void deveBuscarRolePorNomeConvertendoParaMaiusculo() {
        String nomeRole = "admin";
        Role role = new Role();

        when(roleGateway.buscarRolePorNome("ADMIN")).thenReturn(role);

        Role resultado = consultarRoleUseCase.buscarRolePorNome(nomeRole);

        assertNotNull(resultado);
        assertEquals(role, resultado);
        verify(roleGateway).buscarRolePorNome("ADMIN");
    }
}
