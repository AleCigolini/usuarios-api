package br.com.fiap.techchallenge.api.usuario.common.interfaces;

import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioDatabase {
    JpaUsuarioEntity salvarUsuario(JpaUsuarioEntity usuario);
    List<JpaUsuarioEntity> buscarUsuarioPorCpf(String cpf);
    Optional<JpaUsuarioEntity> buscarUsuarioPorId(UUID id);
    List<JpaUsuarioEntity> buscarUsuarioPorEmail(String email);
    List<JpaUsuarioEntity> buscarUsuarioPorEmailECpf(String email, String cpf);
}
