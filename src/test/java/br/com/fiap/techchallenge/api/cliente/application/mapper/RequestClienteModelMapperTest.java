package br.com.fiap.techchallenge.api.cliente.application.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.techchallenge.api.cliente.application.mapper.modelmapper.RequestClienteModelMapper;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
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
class RequestClienteModelMapperTest {

    private RequestClienteModelMapper requestClienteModelMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        requestClienteModelMapper = new RequestClienteModelMapper(modelMapper);
    }

    @Test
    void deveMapearParaJpaClienteEntity() {
        ClienteRequestDto dto = new ClienteRequestDto();
        Cliente cliente = new Cliente();

        when(modelMapper.map(dto, Cliente.class)).thenReturn(cliente);

        Cliente resultado = requestClienteModelMapper.requestDtoToDomain(dto);

        assertNotNull(resultado);
        assertEquals(cliente, resultado);
        verify(modelMapper).map(dto, Cliente.class);
    }

    @Test
    void deveLancarValidacaoEntidadeExceptionAoMapearParaJpaClienteEntity() {
        ClienteRequestDto dto = new ClienteRequestDto();

        ValidacaoEntidadeException validacaoException = new ValidacaoEntidadeException("Erro de validação");
        ErrorMessage errorMessage = new ErrorMessage("VALIDACAO", validacaoException);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Cliente.class)).thenThrow(mappingException);

        ValidacaoEntidadeException ex = assertThrows(ValidacaoEntidadeException.class, () -> {
            requestClienteModelMapper.requestDtoToDomain(dto);
        });

        assertEquals("Erro de validação", ex.getMessage());
        verify(modelMapper).map(dto, Cliente.class);
    }

    @Test
    void deveLancarMappingExceptionAoMapearParaJpaClienteEntityQuandoErroNaoForValidacao() {
        ClienteRequestDto dto = new ClienteRequestDto();

        RuntimeException outraCausa = new RuntimeException("Outra causa");
        ErrorMessage errorMessage = new ErrorMessage("ERRO", outraCausa);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Cliente.class)).thenThrow(mappingException);

        MappingException ex = assertThrows(MappingException.class, () -> {
            requestClienteModelMapper.requestDtoToDomain(dto);
        });

        assertTrue(ex.getErrorMessages().stream()
                .anyMatch(msg -> msg.getCause() == outraCausa));
        verify(modelMapper).map(dto, Cliente.class);
    }

    @Test
    void deveMapearIdentificarClienteDtoParaJpaClienteEntity() {
        IdentificarClienteDto dto = new IdentificarClienteDto();
        Cliente cliente = new Cliente();

        when(modelMapper.map(dto, Cliente.class)).thenReturn(cliente);

        Cliente resultado = requestClienteModelMapper.requestDtoToDomain(dto);

        assertNotNull(resultado);
        assertEquals(cliente, resultado);
        verify(modelMapper).map(dto, Cliente.class);
    }

    @Test
    void deveLancarValidacaoEntidadeExceptionAoMapearIdentificarClienteDtoParaJpaClienteEntity() {
        IdentificarClienteDto dto = new IdentificarClienteDto();

        ValidacaoEntidadeException validacaoException = new ValidacaoEntidadeException("Erro de validação");
        ErrorMessage errorMessage = new ErrorMessage("VALIDACAO", validacaoException);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Cliente.class)).thenThrow(mappingException);

        ValidacaoEntidadeException ex = assertThrows(ValidacaoEntidadeException.class, () -> {
            requestClienteModelMapper.requestDtoToDomain(dto);
        });

        assertEquals("Erro de validação", ex.getMessage());
        verify(modelMapper).map(dto, Cliente.class);
    }

    @Test
    void deveLancarMappingExceptionAoMapearIdentificarClienteDtoParaJpaClienteEntityQuandoErroNaoForValidacao() {
        IdentificarClienteDto dto = new IdentificarClienteDto();

        RuntimeException outraCausa = new RuntimeException("Outra causa");
        ErrorMessage errorMessage = new ErrorMessage("ERRO", outraCausa);
        MappingException mappingException = new MappingException(Collections.singletonList(errorMessage));

        when(modelMapper.map(dto, Cliente.class)).thenThrow(mappingException);

        MappingException ex = assertThrows(MappingException.class, () -> {
            requestClienteModelMapper.requestDtoToDomain(dto);
        });

        assertTrue(ex.getErrorMessages().stream()
                .anyMatch(msg -> msg.getCause() == outraCausa));
        verify(modelMapper).map(dto, Cliente.class);
    }
    
}
