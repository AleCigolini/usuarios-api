package br.com.fiap.techchallenge.api.usuario.application.usecase.impl;

import br.com.fiap.techchallenge.api.usuario.application.usecase.ValidarSenhaUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Logger;

@AllArgsConstructor
public class ValidarSenhaUsuarioUseCaseImpl implements ValidarSenhaUsuarioUseCase {
    private final PasswordEncoder passwordEncoder;
    private static final Logger LOGGER = Logger.getLogger(ValidarSenhaUsuarioUseCaseImpl.class.getName());

    @Override
    public Boolean isSenhaValida(String senhaSalva, String senhaInformada) {
        LOGGER.info("Validando senha");
        return passwordEncoder.matches(senhaInformada, senhaSalva);
    }
}
