package br.com.fiap.techchallenge.api.usuario.application.usecase.impl;

import br.com.fiap.techchallenge.api.usuario.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.usecase.SalvarUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioValidacaoException;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class SalvarUsuarioUseCaseImpl implements SalvarUsuarioUseCase {
    private UsuarioGateway usuarioGateway;
    private AuthenticationGateway authenticationGateway;

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        this.validarUsuarioExistente(usuario);
        cadastrarUsuarioAutenticacao(usuario);

        return usuarioGateway.salvarUsuario(usuario);
    }

    private void cadastrarUsuarioAutenticacao(Usuario usuario) {
        if (!authenticationGateway.isUsuarioExistente(usuario)) {
            authenticationGateway.cadastrarUsuario(usuario);
        }
    }

    private void validarUsuarioExistente(Usuario usuario) {
        List<String> erros = new ArrayList<>();

        validarDuplicidade(usuario.getCpf(), usuarioGateway::buscarUsuarioPorCpf, "Já existe um usuario cadastrado com o CPF informado.", erros);
        validarDuplicidade(usuario.getEmail(), usuarioGateway::buscarUsuarioPorEmail, "Já existe um usuario cadastrado com o e-mail informado.", erros);

        if (!erros.isEmpty()) {
            throw new UsuarioValidacaoException(String.join(", ", erros));
        }
    }

    private <T> void validarDuplicidade(T campo, Function<T, List<Usuario>>busca, String mensagemErro, List<String> erros) {
        if (campo != null) {
            List<Usuario> usuarioEncontrados = busca.apply(campo);
            if (!usuarioEncontrados.isEmpty()) {
                erros.add(mensagemErro);
            }
        }
    }
}
