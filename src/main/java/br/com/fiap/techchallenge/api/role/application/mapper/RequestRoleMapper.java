package br.com.fiap.techchallenge.api.role.application.mapper;

import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.domain.Role;

public interface RequestRoleMapper {
    Role requestDtoToDomain(RoleRequestDto roleRequestDto);
}
