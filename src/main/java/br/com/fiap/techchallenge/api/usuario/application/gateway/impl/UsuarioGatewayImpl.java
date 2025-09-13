package br.com.fiap.techchallenge.api.usuario.application.gateway.impl;

import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.mapper.DatabaseUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioDatabase;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {
    private UsuarioDatabase usuarioDatabase;
    private DatabaseUsuarioMapper mapper;
    private DatabaseRoleMapper databaseRoleMapper;

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        final var role = databaseRoleMapper.toJpaRoleEntity(usuario.getRole());
        final var usuarioEntity = mapper.toJpaUsuarioEntity(usuario);
        usuarioEntity.setJpaRoleEntity(role);
        return mapper.toUsuario(usuarioDatabase.salvarUsuario(usuarioEntity));
    }

    @Override
    public List<Usuario> buscarUsuarioPorCpf(Cpf cpf) {
        return usuarioDatabase.buscarUsuarioPorCpf(cpf.getValue()).stream()
                .map(jpaUsuarioEntity -> mapper.toUsuario(jpaUsuarioEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(UUID id) {
        return usuarioDatabase.buscarUsuarioPorId(id).map(jpaUsuarioEntity -> mapper.toUsuario(jpaUsuarioEntity));
    }

    @Override
    public List<Usuario> buscarUsuarioPorEmail(Email email) {
        return usuarioDatabase.buscarUsuarioPorEmail(email.getEndereco()).stream()
                .map(jpaUsuarioEntity -> mapper.toUsuario(jpaUsuarioEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> buscarUsuarioPorEmailECpf(Email email, Cpf cpf) {
        return usuarioDatabase.buscarUsuarioPorEmailECpf(email.getEndereco(), cpf.getValue()).stream()
                .map(jpaUsuarioEntity -> mapper.toUsuario(jpaUsuarioEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorLogin(String login) {
        return usuarioDatabase.buscarUsuarioPorLogin(login).map(jpaUsuarioEntity -> mapper.toUsuario(jpaUsuarioEntity));
    }
}
