package br.com.fiap.techchallenge.api.usuario.application.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.techchallenge.api.usuario.application.mapper.modelmapper.RequestUsuarioModelMapper;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.config.exception.exceptions.ValidacaoEntidadeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class RequestUsuarioModelMapperTest {

    private RequestUsuarioModelMapper requestUsuarioModelMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        requestUsuarioModelMapper = new RequestUsuarioModelMapper(modelMapper);
    }

    @Test
    void deveMapearParaJpaUsuarioEntity() {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        Usuario usuario = new Usuario();

        when(modelMapper.map(dto, Usuario.class)).thenReturn(usuario);

        Usuario resultado = requestUsuarioModelMapper.requestDtoToDomain(dto);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
        verify(modelMapper).map(dto, Usuario.class);
    }

    @Test
    void deveLancarValidacaoEntidadeExceptionAoMapearParaJpaUsuarioEntity() {
        UsuarioRequestDto dto = new UsuarioRequestDto();

        ValidacaoEntidadeException validacaoException = new ValidacaoEntidadeException("Erro de validação");
        ErrorMessage errorMessage = new ErrorMessage("VALIDACAO", validacaoException);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Usuario.class)).thenThrow(mappingException);

        ValidacaoEntidadeException ex = assertThrows(ValidacaoEntidadeException.class, () -> {
            requestUsuarioModelMapper.requestDtoToDomain(dto);
        });

        assertEquals("Erro de validação", ex.getMessage());
        verify(modelMapper).map(dto, Usuario.class);
    }

    @Test
    void deveLancarMappingExceptionAoMapearParaJpaUsuarioEntityQuandoErroNaoForValidacao() {
        UsuarioRequestDto dto = new UsuarioRequestDto();

        RuntimeException outraCausa = new RuntimeException("Outra causa");
        ErrorMessage errorMessage = new ErrorMessage("ERRO", outraCausa);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Usuario.class)).thenThrow(mappingException);

        MappingException ex = assertThrows(MappingException.class, () -> {
            requestUsuarioModelMapper.requestDtoToDomain(dto);
        });

        assertTrue(ex.getErrorMessages().stream()
                .anyMatch(msg -> msg.getCause() == outraCausa));
        verify(modelMapper).map(dto, Usuario.class);
    }

    @Test
    void deveMapearIdentificarUsuarioDtoParaJpaUsuarioEntity() {
        IdentificarUsuarioDto dto = new IdentificarUsuarioDto();
        Usuario usuario = new Usuario();

        when(modelMapper.map(dto, Usuario.class)).thenReturn(usuario);

        Usuario resultado = requestUsuarioModelMapper.requestDtoToDomain(dto);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
        verify(modelMapper).map(dto, Usuario.class);
    }

    @Test
    void deveLancarValidacaoEntidadeExceptionAoMapearIdentificarUsuarioDtoParaJpaUsuarioEntity() {
        IdentificarUsuarioDto dto = new IdentificarUsuarioDto();

        ValidacaoEntidadeException validacaoException = new ValidacaoEntidadeException("Erro de validação");
        ErrorMessage errorMessage = new ErrorMessage("VALIDACAO", validacaoException);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Usuario.class)).thenThrow(mappingException);

        ValidacaoEntidadeException ex = assertThrows(ValidacaoEntidadeException.class, () -> {
            requestUsuarioModelMapper.requestDtoToDomain(dto);
        });

        assertEquals("Erro de validação", ex.getMessage());
        verify(modelMapper).map(dto, Usuario.class);
    }

    @Test
    void deveLancarMappingExceptionAoMapearIdentificarUsuarioDtoParaJpaUsuarioEntityQuandoErroNaoForValidacao() {
        IdentificarUsuarioDto dto = new IdentificarUsuarioDto();

        RuntimeException outraCausa = new RuntimeException("Outra causa");
        ErrorMessage errorMessage = new ErrorMessage("ERRO", outraCausa);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Usuario.class)).thenThrow(mappingException);

        MappingException ex = assertThrows(MappingException.class, () -> {
            requestUsuarioModelMapper.requestDtoToDomain(dto);
        });

        assertTrue(ex.getErrorMessages().stream()
                .anyMatch(msg -> msg.getCause() == outraCausa));
        verify(modelMapper).map(dto, Usuario.class);
    }
    
}
