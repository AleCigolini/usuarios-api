package br.com.fiap.techchallenge.api.cliente.application.presenter;

import br.com.fiap.techchallenge.api.cliente.common.domain.dto.response.ClienteResponseDto;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;

public interface ClientePresenter {
    ClienteResponseDto toResponse(Cliente usuario);
}
