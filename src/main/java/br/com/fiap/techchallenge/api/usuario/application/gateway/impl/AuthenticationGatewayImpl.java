package br.com.fiap.techchallenge.api.usuario.application.gateway.impl;

import br.com.fiap.techchallenge.api.usuario.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioAuthentication;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationGatewayImpl implements AuthenticationGateway {
    private final UsuarioAuthentication usuarioAuthentication;

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        usuarioAuthentication.salvarUsuarioAuthentication(usuario);
    }

    @Override
    public boolean isUsuarioExistente(Usuario usuario) {
        return usuarioAuthentication.isUsuarioExistente(usuario);
    }
}
