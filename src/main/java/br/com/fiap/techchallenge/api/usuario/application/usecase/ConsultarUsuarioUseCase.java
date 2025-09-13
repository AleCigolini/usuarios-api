package br.com.fiap.techchallenge.api.usuario.application.usecase;

import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;

import java.util.UUID;

public interface ConsultarUsuarioUseCase {
    Usuario buscarUsuarioPorCpf(Cpf cpf);

    Usuario buscarUsuarioPorId(UUID id);

    Usuario buscarUsuarioPorEmail(Email email);

    Usuario buscarUsuarioPorEmailCpf(Cpf cpf, Email email, Boolean throwExceptionWhenNotFound);
    Usuario buscarUsuarioPorLogin(String login, Boolean throwExceptionWhenNotFound);
}
