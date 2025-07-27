package br.com.fiap.techchallenge.api.role.application.mapper;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.ValidacaoEntidadeException;
import br.com.fiap.techchallenge.api.role.application.mapper.modelmapper.RequestRoleModelMapper;
import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestRoleModelMapperTest {

    private RequestRoleModelMapper requestRoleModelMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        requestRoleModelMapper = new RequestRoleModelMapper(modelMapper);
    }

    @Test
    void deveMapearParaJpaRoleEntity() {
        RoleRequestDto dto = new RoleRequestDto();
        Role role = new Role();

        when(modelMapper.map(dto, Role.class)).thenReturn(role);

        Role resultado = requestRoleModelMapper.requestDtoToDomain(dto);

        assertNotNull(resultado);
        assertEquals(role, resultado);
        verify(modelMapper).map(dto, Role.class);
    }

    @Test
    void deveLancarValidacaoEntidadeExceptionAoMapearParaJpaRoleEntity() {
        RoleRequestDto dto = new RoleRequestDto();

        ValidacaoEntidadeException validacaoException = new ValidacaoEntidadeException("Erro de validação");
        ErrorMessage errorMessage = new ErrorMessage("VALIDACAO", validacaoException);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Role.class)).thenThrow(mappingException);

        ValidacaoEntidadeException ex = assertThrows(ValidacaoEntidadeException.class, () -> {
            requestRoleModelMapper.requestDtoToDomain(dto);
        });

        assertEquals("Erro de validação", ex.getMessage());
        verify(modelMapper).map(dto, Role.class);
    }

    @Test
    void deveLancarMappingExceptionAoMapearParaJpaRoleEntityQuandoErroNaoForValidacao() {
        RoleRequestDto dto = new RoleRequestDto();

        RuntimeException outraCausa = new RuntimeException("Outra causa");
        ErrorMessage errorMessage = new ErrorMessage("ERRO", outraCausa);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Role.class)).thenThrow(mappingException);

        MappingException ex = assertThrows(MappingException.class, () -> {
            requestRoleModelMapper.requestDtoToDomain(dto);
        });

        assertTrue(ex.getErrorMessages().stream()
                .anyMatch(msg -> msg.getCause() == outraCausa));
        verify(modelMapper).map(dto, Role.class);
    }

}
