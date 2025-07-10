package br.com.fiap.techchallenge03.cliente.common.domain.exception;

import br.com.fiap.techchallenge03.core.config.exception.exceptions.NegocioException;

public class ClienteValidacaoException extends NegocioException {

    public ClienteValidacaoException(String mensagem) {
        super(mensagem);
    }

}
