package br.com.fiap.techchallenge.api.role.application.presenter;

import br.com.fiap.techchallenge.api.role.application.presenter.impl.RolePresenterImpl;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import br.com.fiap.techchallenge.api.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RolePresenterTest {

    private RolePresenterImpl rolePresenter;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        rolePresenter = new RolePresenterImpl(modelMapper);
    }

    @Test
    void deveConverterRoleParaRoleResponseDto() {
        Role role = new Role();
        RoleResponseDto responseDto = new RoleResponseDto();

        when(modelMapper.map(role, RoleResponseDto.class)).thenReturn(responseDto);

        RoleResponseDto resultado = rolePresenter.toResponse(role);

        assertNotNull(resultado);
        assertEquals(responseDto, resultado);
        verify(modelMapper).map(role, RoleResponseDto.class);
    }
}
