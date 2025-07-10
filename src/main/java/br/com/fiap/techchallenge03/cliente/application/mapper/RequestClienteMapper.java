package br.com.fiap.techchallenge03.cliente.application.mapper;

import br.com.fiap.techchallenge03.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge03.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge03.cliente.domain.Cliente;

public interface RequestClienteMapper {
    Cliente requestDtoToDomain(ClienteRequestDto clienteRequestDto);
    Cliente requestDtoToDomain(IdentificarClienteDto identificarClienteDto);
}
