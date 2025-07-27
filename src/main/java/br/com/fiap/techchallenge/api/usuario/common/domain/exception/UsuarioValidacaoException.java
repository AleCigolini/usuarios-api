package br.com.fiap.techchallenge.api.usuario.common.domain.exception;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.NegocioException;

public class UsuarioValidacaoException extends NegocioException {

    public UsuarioValidacaoException(String mensagem) {
        super(mensagem);
    }

}
