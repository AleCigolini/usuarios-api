package br.com.fiap.techchallenge.api.usuario.application.gateway;

import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioGateway {
    Usuario salvarUsuario(Usuario usuario);
    List<Usuario> buscarUsuarioPorCpf(Cpf cpf);
    Optional<Usuario> buscarUsuarioPorId(UUID id);
    List<Usuario> buscarUsuarioPorEmail(Email email);
    List<Usuario> buscarUsuarioPorEmailECpf(Email email, Cpf cpf);

    Optional<Usuario> buscarUsuarioPorLogin(String login);
}
