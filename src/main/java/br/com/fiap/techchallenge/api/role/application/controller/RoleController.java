package br.com.fiap.techchallenge.api.role.application.controller;

import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;

import java.util.List;

public interface RoleController {
    RoleResponseDto cadastrarRole(RoleRequestDto roleRequestDto);
    RoleResponseDto buscarRolePorNome(String role);
    RoleResponseDto habilitarRolePorNome(String role);
    void excluirRolePorNome(String role);
    List<RoleResponseDto> consultarRoles();
}
