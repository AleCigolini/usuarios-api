package br.com.fiap.techchallenge.api.usuario.application.usecase.impl;

import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.usecase.ConsultarUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioValidacaoException;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsultarUsuarioUseCaseImpl implements ConsultarUsuarioUseCase {
    private UsuarioGateway usuarioGateway;

    @Override
    public Usuario buscarUsuarioPorCpf(Cpf cpf) {
        List<Usuario> usuariosEncontradosPorCpf = usuarioGateway.buscarUsuarioPorCpf(cpf);
        validarListaUsuarioUnicoEncontrado(usuariosEncontradosPorCpf, "cpf", cpf.getValue());
        return usuariosEncontradosPorCpf.getFirst();
    }

    @Override
    public Usuario buscarUsuarioPorId(UUID id) {
        Usuario usuario = usuarioGateway.buscarUsuarioPorId(id)
                .orElse(null);
        if (usuario == null) {
            this.throwUsuarioNaoEncontradoException("id", id.toString());
        }
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(Email email) {
        List<Usuario> usuariosEncontradosPorEmail = usuarioGateway.buscarUsuarioPorEmail(email);
        validarListaUsuarioUnicoEncontrado(usuariosEncontradosPorEmail, "email", email.getEndereco());
        return usuariosEncontradosPorEmail.getFirst();
    }

    @Override
    public Usuario buscarUsuarioPorEmailCpf(Cpf cpf, Email email, Boolean throwExceptionWhenNotFound) {
        List<Usuario> usuarios = buscaUsuariosEmailCpf(cpf, email);
        Boolean isUsuariosEmpty = usuarios.isEmpty();
        if (isUsuariosEmpty && throwExceptionWhenNotFound) {
            throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuario");
        }
        return isUsuariosEmpty ? null : usuarios.getFirst();
    }

    private List<Usuario> buscaUsuariosEmailCpf(Cpf cpf, Email email) {
        if (cpf == null && email == null) {
            throw new UsuarioValidacaoException("Pelo menos um dos campos (cpf ou email) deve ser informado.");
        }

        if (cpf != null && email != null) {
            return usuarioGateway.buscarUsuarioPorEmailECpf(email, cpf);
        }
        if (cpf != null) {
            return usuarioGateway.buscarUsuarioPorCpf(cpf);
        }
        return usuarioGateway.buscarUsuarioPorEmail(email);
    }

    private void validarListaUsuarioUnicoEncontrado(List<Usuario> usuarios, String campoBusca, String valorBusca) {
        if (usuarios.isEmpty()) {
            this.throwUsuarioNaoEncontradoException(campoBusca, valorBusca);
        }
        if (usuarios.size() > 1) {
            throw new UsuarioValidacaoException(String.format("Encontrado mais de um usuario para o %s: %s", campoBusca, valorBusca));
        }
    }

    private void throwUsuarioNaoEncontradoException(String campoBusca, String valorBusca) {
        throw new UsuarioNaoEncontradoException(String.format("Não foi encontrado nenhum usuario para o %s: %s", campoBusca, valorBusca));
    }
}
