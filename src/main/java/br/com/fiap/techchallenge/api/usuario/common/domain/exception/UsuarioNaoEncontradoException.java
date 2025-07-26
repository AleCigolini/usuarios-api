package br.com.fiap.techchallenge.api.usuario.common.domain.exception;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.EntidadeNaoEncontradaException;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
