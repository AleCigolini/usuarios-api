package br.com.fiap.techchallenge.api.usuario.common.interfaces;

import br.com.fiap.techchallenge.api.usuario.domain.Usuario;

public interface UsuarioAuthentication {
    void salvarUsuarioAuthentication(Usuario usuario);
    boolean isUsuarioExistente(Usuario usuario);
}
