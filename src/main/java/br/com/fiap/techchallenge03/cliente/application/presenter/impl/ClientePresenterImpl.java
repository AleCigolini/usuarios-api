package br.com.fiap.techchallenge03.cliente.application.presenter.impl;

import br.com.fiap.techchallenge03.cliente.application.presenter.ClientePresenter;
import br.com.fiap.techchallenge03.cliente.common.domain.dto.response.ClienteResponseDto;
import br.com.fiap.techchallenge03.cliente.domain.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientePresenterImpl implements ClientePresenter {
    private ModelMapper modelMapper;

    public ClienteResponseDto toResponse(Cliente usuario) {
        return modelMapper.map(usuario, ClienteResponseDto.class);
    }
}
