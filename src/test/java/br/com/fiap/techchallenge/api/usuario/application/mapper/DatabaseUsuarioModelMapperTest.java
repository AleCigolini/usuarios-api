package br.com.fiap.techchallenge.api.usuario.application.mapper;

import br.com.fiap.techchallenge.api.usuario.application.mapper.modelmapper.DatabaseUsuarioModelMapper;
import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DatabaseUsuarioModelMapperTest {
    private ModelMapper modelMapper;
    private DatabaseUsuarioModelMapper databaseUsuarioModelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        databaseUsuarioModelMapper = new DatabaseUsuarioModelMapper(modelMapper);
    }

    @Test
    void deveMapearParaJpaUsuarioEntity() {
        Usuario usuario = new Usuario();
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        when(modelMapper.map(usuario, JpaUsuarioEntity.class)).thenReturn(entity);

        JpaUsuarioEntity result = databaseUsuarioModelMapper.toJpaUsuarioEntity(usuario);

        assertThat(result).isEqualTo(entity);
        verify(modelMapper).map(usuario, JpaUsuarioEntity.class);
    }

    @Test
    void deveMapearParaUsuario() {
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        Usuario usuario = new Usuario();
        when(modelMapper.map(entity, Usuario.class)).thenReturn(usuario);

        Usuario result = databaseUsuarioModelMapper.toUsuario(entity);

        assertThat(result).isEqualTo(usuario);
        verify(modelMapper).map(entity, Usuario.class);
    }
}
