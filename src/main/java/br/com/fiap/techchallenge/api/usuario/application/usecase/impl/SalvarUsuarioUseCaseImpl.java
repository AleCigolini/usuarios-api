package br.com.fiap.techchallenge.api.usuario.application.usecase.impl;

import br.com.fiap.techchallenge.api.role.domain.Role;
import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.usecase.SalvarUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioValidacaoException;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class SalvarUsuarioUseCaseImpl implements SalvarUsuarioUseCase {
    private UsuarioGateway usuarioGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario salvarUsuario(Usuario usuario, Role role) {
        this.validarUsuarioExistente(usuario);
        encriptarSenha(usuario);

        usuario.setRole(role);

        return usuarioGateway.salvarUsuario(usuario);
    }

    private void encriptarSenha(Usuario usuario) {
        String senhaEncriptada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncriptada);
    }

    private void validarUsuarioExistente(Usuario usuario) {
        List<String> erros = new ArrayList<>();

        if (usuario.getCpf() != null) {
            validarDuplicidade(usuario.getCpf(), usuarioGateway::buscarUsuarioPorCpf, "Já existe um usuario cadastrado com o CPF informado.", erros);
        }
        if(usuario.getEmail() != null) {
            validarDuplicidade(usuario.getEmail(), usuarioGateway::buscarUsuarioPorEmail, "Já existe um usuario cadastrado com o e-mail informado.", erros);
        }
        usuarioGateway.buscarUsuarioPorLogin(usuario.getLogin()).ifPresent(usuarioEncontrado -> erros.add("Já existe um usuario cadastrado com o login informado."));

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
