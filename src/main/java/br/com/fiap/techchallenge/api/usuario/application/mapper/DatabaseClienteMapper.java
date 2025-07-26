package br.com.fiap.techchallenge.api.usuario.application.mapper;

import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;

public interface DatabaseUsuarioMapper {
    JpaUsuarioEntity toJpaUsuarioEntity(Usuario usuario);
    Usuario toUsuario(JpaUsuarioEntity jpaUsuarioEntity);
}