package br.com.fiap.techchallenge.api.usuario.application.gateway;

import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.usuario.application.gateway.impl.UsuarioGatewayImpl;
import br.com.fiap.techchallenge.api.usuario.application.mapper.DatabaseUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioDatabase;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class UsuarioGatewayImplTest {
    private static final String CPF = "89670770092";

    private UsuarioDatabase usuarioDatabase;
    private DatabaseUsuarioMapper mapper;
    private UsuarioGatewayImpl usuarioGateway;
    private DatabaseRoleMapper databaseRoleMapper;

    @BeforeEach
    void setUp() {
        usuarioDatabase = mock(UsuarioDatabase.class);
        mapper = mock(DatabaseUsuarioMapper.class);
        databaseRoleMapper = mock(DatabaseRoleMapper.class);
        when(databaseRoleMapper.toJpaRoleEntity(any())).thenReturn(new JpaRoleEntity());
        usuarioGateway = new UsuarioGatewayImpl(usuarioDatabase, mapper, databaseRoleMapper);
    }

    @Test
    void deveSalvarUsuario() {
        Usuario usuario = new Usuario();
        JpaUsuarioEntity usuarioEntity = new JpaUsuarioEntity();
        when(mapper.toJpaUsuarioEntity(usuario)).thenReturn(usuarioEntity);
        when(usuarioDatabase.salvarUsuario(usuarioEntity)).thenReturn(usuarioEntity);
        when(mapper.toUsuario(usuarioEntity)).thenReturn(usuario);

        Usuario result = usuarioGateway.salvarUsuario(usuario);

        assertThat(result).isEqualTo(usuario);
        verify(mapper).toJpaUsuarioEntity(usuario);
        verify(usuarioDatabase).salvarUsuario(usuarioEntity);
        verify(mapper).toUsuario(usuarioEntity);
    }

    @Test
    void deveBuscarUsuarioPorCpf() {
        Cpf cpf = new Cpf(CPF);
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        Usuario usuario = new Usuario();
        when(usuarioDatabase.buscarUsuarioPorCpf(cpf.getValue())).thenReturn(List.of(entity));
        when(mapper.toUsuario(entity)).thenReturn(usuario);

        List<Usuario> result = usuarioGateway.buscarUsuarioPorCpf(cpf);

        assertThat(result).containsExactly(usuario);
        verify(usuarioDatabase).buscarUsuarioPorCpf(cpf.getValue());
        verify(mapper).toUsuario(entity);
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UUID id = UUID.randomUUID();
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        Usuario usuario = new Usuario();
        when(usuarioDatabase.buscarUsuarioPorId(id)).thenReturn(Optional.of(entity));
        when(mapper.toUsuario(entity)).thenReturn(usuario);

        Optional<Usuario> result = usuarioGateway.buscarUsuarioPorId(id);

        assertThat(result).contains(usuario);
        verify(usuarioDatabase).buscarUsuarioPorId(id);
        verify(mapper).toUsuario(entity);
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        Email email = new Email("teste@teste.com");
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        Usuario usuario = new Usuario();
        when(usuarioDatabase.buscarUsuarioPorEmail(email.getEndereco())).thenReturn(List.of(entity));
        when(mapper.toUsuario(entity)).thenReturn(usuario);

        List<Usuario> result = usuarioGateway.buscarUsuarioPorEmail(email);

        assertThat(result).containsExactly(usuario);
        verify(usuarioDatabase).buscarUsuarioPorEmail(email.getEndereco());
        verify(mapper).toUsuario(entity);
    }

    @Test
    void deveBuscarUsuarioPorEmailECpf() {
        Email email = new Email("teste@teste.com");
        Cpf cpf = new Cpf(CPF);
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        Usuario usuario = new Usuario();
        when(usuarioDatabase.buscarUsuarioPorEmailECpf(email.getEndereco(), cpf.getValue())).thenReturn(List.of(entity));
        when(mapper.toUsuario(entity)).thenReturn(usuario);

        List<Usuario> result = usuarioGateway.buscarUsuarioPorEmailECpf(email, cpf);

        assertThat(result).containsExactly(usuario);
        verify(usuarioDatabase).buscarUsuarioPorEmailECpf(email.getEndereco(), cpf.getValue());
        verify(mapper).toUsuario(entity);
    }
}
