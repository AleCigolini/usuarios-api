package br.com.fiap.techchallenge.api.cliente.common.domain.exception;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.EntidadeNaoEncontradaException;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
