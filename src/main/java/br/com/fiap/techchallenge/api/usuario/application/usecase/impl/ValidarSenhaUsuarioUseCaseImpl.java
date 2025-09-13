package br.com.fiap.techchallenge.api.usuario.application.usecase.impl;

import br.com.fiap.techchallenge.api.usuario.application.usecase.ValidarSenhaUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class ValidarSenhaUsuarioUseCaseImpl implements ValidarSenhaUsuarioUseCase {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean isSenhaValida(String senhaSalva, String senhaInformada) {
        return passwordEncoder.matches(senhaInformada, senhaSalva);
    }
}
