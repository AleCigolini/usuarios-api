package br.com.fiap.techchallenge.api.role.application.presenter.impl;

import br.com.fiap.techchallenge.api.role.application.presenter.RolePresenter;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import br.com.fiap.techchallenge.api.role.domain.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RolePresenterImpl implements RolePresenter {
    private ModelMapper modelMapper;

    public RoleResponseDto toResponse(Role role) {
        return modelMapper.map(role, RoleResponseDto.class);
    }
}
