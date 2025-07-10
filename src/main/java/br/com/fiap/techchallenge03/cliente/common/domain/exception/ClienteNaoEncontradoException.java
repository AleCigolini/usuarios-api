package br.com.fiap.techchallenge03.cliente.common.domain.exception;

import br.com.fiap.techchallenge03.core.config.exception.exceptions.EntidadeNaoEncontradaException;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
