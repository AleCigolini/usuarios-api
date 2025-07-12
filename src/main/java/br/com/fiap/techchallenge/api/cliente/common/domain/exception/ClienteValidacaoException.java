package br.com.fiap.techchallenge.api.cliente.common.domain.exception;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.NegocioException;

public class ClienteValidacaoException extends NegocioException {

    public ClienteValidacaoException(String mensagem) {
        super(mensagem);
    }

}
