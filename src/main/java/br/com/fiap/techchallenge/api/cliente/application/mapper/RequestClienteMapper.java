package br.com.fiap.techchallenge.api.cliente.application.mapper;

import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;

public interface RequestClienteMapper {
    Cliente requestDtoToDomain(ClienteRequestDto clienteRequestDto);
    Cliente requestDtoToDomain(IdentificarClienteDto identificarClienteDto);
}
