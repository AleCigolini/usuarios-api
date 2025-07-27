package br.com.fiap.techchallenge.api.usuario.application.mapper.modelmapper;

import br.com.fiap.techchallenge.api.usuario.application.mapper.DatabaseUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseUsuarioModelMapper implements DatabaseUsuarioMapper {
    private ModelMapper modelMapper;

    public JpaUsuarioEntity toJpaUsuarioEntity(Usuario usuario) {
        return modelMapper.map(usuario, JpaUsuarioEntity.class);
    }

    public Usuario toUsuario(JpaUsuarioEntity jpaUsuarioEntity) {
        return modelMapper.map(jpaUsuarioEntity, Usuario.class);
    }
}