package br.com.fiap.techchallenge03.cliente.application.controller;

import br.com.fiap.techchallenge03.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge03.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge03.cliente.common.domain.dto.response.ClienteResponseDto;

import java.util.UUID;

public interface ClienteController {
    ClienteResponseDto buscarClientePorCpf(String cpf);
    ClienteResponseDto buscarClientePorEmail(String email);
    ClienteResponseDto buscarClientePorId(UUID id);
    ClienteResponseDto cadastrarCliente(ClienteRequestDto clienteRequestDto);
    ClienteResponseDto identificarCliente(IdentificarClienteDto identificarClienteDto);
}
