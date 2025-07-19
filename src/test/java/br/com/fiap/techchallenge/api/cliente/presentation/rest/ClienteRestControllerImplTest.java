package br.com.fiap.techchallenge.api.cliente.presentation.rest;

import br.com.fiap.techchallenge.api.cliente.application.controller.ClienteController;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.response.ClienteResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClienteRestControllerImplTest {
    @Mock
    private ClienteController clienteController;

    @InjectMocks
    private ClienteRestControllerImpl clienteRestController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarClientePorCpf_quandoCpfValido() {
        String cpf = "89670770092";
        ClienteResponseDto esperado = new ClienteResponseDto();

        when(clienteController.buscarClientePorCpf(cpf)).thenReturn(esperado);

        ClienteResponseDto resultado = clienteRestController.buscarClientePorCpf(cpf);

        assertEquals(esperado, resultado);
        verify(clienteController).buscarClientePorCpf(cpf);
    }

    @Test
    void deveBuscarClientePorEmail_quandoEmailValido() {
        String email = "teste@teste.com";
        ClienteResponseDto esperado = new ClienteResponseDto();

        when(clienteController.buscarClientePorEmail(email)).thenReturn(esperado);

        ClienteResponseDto resultado = clienteRestController.buscarClientePorEmail(email);

        assertEquals(esperado, resultado);
        verify(clienteController).buscarClientePorEmail(email);
    }

    @Test
    void deveBuscarClientePorId_quandoIdValido() {
        UUID id = UUID.randomUUID();
        ClienteResponseDto esperado = new ClienteResponseDto();

        when(clienteController.buscarClientePorId(id)).thenReturn(esperado);

        ClienteResponseDto resultado = clienteRestController.buscarClientePorId(id);

        assertEquals(esperado, resultado);
        verify(clienteController).buscarClientePorId(id);
    }

    @Test
    void deveCadastrarCliente_quandoRequestValido() {
        ClienteRequestDto requestDto = new ClienteRequestDto();
        ClienteResponseDto esperado = new ClienteResponseDto();

        when(clienteController.cadastrarCliente(requestDto)).thenReturn(esperado);

        ClienteResponseDto resultado = clienteRestController.cadastrarCliente(requestDto);

        assertEquals(esperado, resultado);
        verify(clienteController).cadastrarCliente(requestDto);
    }

    @Test
    void deveIdentificarCliente_quandoRequestValido() {
        IdentificarClienteDto requestDto = new IdentificarClienteDto();
        ClienteResponseDto esperado = new ClienteResponseDto();

        when(clienteController.identificarCliente(requestDto)).thenReturn(esperado);

        ClienteResponseDto resultado = clienteRestController.identificarCliente(requestDto);

        assertEquals(esperado, resultado);
        verify(clienteController).identificarCliente(requestDto);
    }
}
