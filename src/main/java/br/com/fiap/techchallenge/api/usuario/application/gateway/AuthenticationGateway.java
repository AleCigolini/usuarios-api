package br.com.fiap.techchallenge.api.usuario.application.gateway;

import br.com.fiap.techchallenge.api.usuario.domain.Usuario;

public interface AuthenticationGateway {
    void cadastrarUsuario(Usuario usuario);
    boolean isUsuarioExistente(Usuario usuario);
}
