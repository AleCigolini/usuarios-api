package br.com.fiap.techchallenge03.cliente.application.presenter;

import br.com.fiap.techchallenge03.cliente.common.domain.dto.response.ClienteResponseDto;
import br.com.fiap.techchallenge03.cliente.domain.Cliente;

public interface ClientePresenter {
    ClienteResponseDto toResponse(Cliente usuario);
}
