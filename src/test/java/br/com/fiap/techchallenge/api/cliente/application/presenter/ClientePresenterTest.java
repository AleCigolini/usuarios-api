package br.com.fiap.techchallenge.api.cliente.application.presenter;

import br.com.fiap.techchallenge.api.cliente.application.presenter.impl.ClientePresenterImpl;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.response.ClienteResponseDto;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ClientePresenterTest {

    private ClientePresenterImpl clientePresenter;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        clientePresenter = new ClientePresenterImpl(modelMapper);
    }

    @Test
    void deveConverterClienteParaClienteResponseDto() {
        Cliente cliente = new Cliente();
        ClienteResponseDto responseDto = new ClienteResponseDto();

        when(modelMapper.map(cliente, ClienteResponseDto.class)).thenReturn(responseDto);

        ClienteResponseDto resultado = clientePresenter.toResponse(cliente);

        assertNotNull(resultado);
        assertEquals(responseDto, resultado);
        verify(modelMapper).map(cliente, ClienteResponseDto.class);
    }
}
