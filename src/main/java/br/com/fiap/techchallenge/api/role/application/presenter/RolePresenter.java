package br.com.fiap.techchallenge.api.role.application.presenter;

import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import br.com.fiap.techchallenge.api.role.domain.Role;

public interface RolePresenter {
    RoleResponseDto toResponse(Role role);
}
