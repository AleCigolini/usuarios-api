package br.com.fiap.techchallenge03.core.utils.domain;

import br.com.fiap.techchallenge03.core.config.exception.exceptions.ValidacaoEntidadeException;

public class Email {
    private final String endereco;

    public Email(String endereco) {
        if (endereco == null || !isValido(endereco)) {
            throw new ValidacaoEntidadeException("E-mail inválido");
        }
        this.endereco = endereco;
    }

    private boolean isValido(String endereco) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{1,}$";
        return endereco.matches(regex);
    }

    public String getEndereco() {
        return endereco;
    }
}
