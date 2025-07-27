package br.com.fiap.techchallenge.api.role.presentation.rest;

import br.com.fiap.techchallenge.api.role.application.controller.RoleController;
import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoleRestControllerImplTest {

    @Mock
    private RoleController roleController;

    private RoleRestControllerImpl roleRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleRestController = new RoleRestControllerImpl(roleController);
    }

    @Test
    void deveCadastrarRole() {
        RoleRequestDto request = new RoleRequestDto();
        RoleResponseDto response = new RoleResponseDto();
        when(roleController.cadastrarRole(request)).thenReturn(response);

        RoleResponseDto resultado = roleRestController.cadastrarRole(request);

        assertEquals(response, resultado);
        verify(roleController).cadastrarRole(request);
    }

    @Test
    void deveBuscarTodasRoles() {
        List<RoleResponseDto> lista = List.of(new RoleResponseDto());
        when(roleController.consultarRoles()).thenReturn(lista);

        List<RoleResponseDto> resultado = roleRestController.buscarRoles();

        assertEquals(lista, resultado);
        verify(roleController).consultarRoles();
    }

    @Test
    void deveBuscarRolePorNome() {
        String nome = "ADMIN";
        RoleResponseDto response = new RoleResponseDto();
        when(roleController.buscarRolePorNome(nome)).thenReturn(response);

        RoleResponseDto resultado = roleRestController.buscarRolePorNome(nome);

        assertEquals(response, resultado);
        verify(roleController).buscarRolePorNome(nome);
    }

    @Test
    void deveHabilitarRolePorNome() {
        String nome = "ADMIN";
        RoleResponseDto response = new RoleResponseDto();
        when(roleController.habilitarRolePorNome(nome)).thenReturn(response);

        RoleResponseDto resultado = roleRestController.habilitarRolePorNome(nome);

        assertEquals(response, resultado);
        verify(roleController).habilitarRolePorNome(nome);
    }

    @Test
    void deveExcluirRolePorNome() {
        String nome = "ADMIN";

        roleRestController.excluirRolePorNome(nome);

        verify(roleController).excluirRolePorNome(nome);
    }
}
