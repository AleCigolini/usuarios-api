package br.com.fiap.techchallenge.api.cliente.application.mapper;

import br.com.fiap.techchallenge.api.cliente.application.mapper.modelmapper.DatabaseClienteModelMapper;
import br.com.fiap.techchallenge.api.cliente.common.domain.entity.JpaClienteEntity;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DatabaseClienteModelMapperTest {
    private ModelMapper modelMapper;
    private DatabaseClienteModelMapper databaseClienteModelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        databaseClienteModelMapper = new DatabaseClienteModelMapper(modelMapper);
    }

    @Test
    void deveMapearParaJpaClienteEntity() {
        Cliente cliente = new Cliente();
        JpaClienteEntity entity = new JpaClienteEntity();
        when(modelMapper.map(cliente, JpaClienteEntity.class)).thenReturn(entity);

        JpaClienteEntity result = databaseClienteModelMapper.toJpaClienteEntity(cliente);

        assertThat(result).isEqualTo(entity);
        verify(modelMapper).map(cliente, JpaClienteEntity.class);
    }

    @Test
    void deveMapearParaCliente() {
        JpaClienteEntity entity = new JpaClienteEntity();
        Cliente cliente = new Cliente();
        when(modelMapper.map(entity, Cliente.class)).thenReturn(cliente);

        Cliente result = databaseClienteModelMapper.toCliente(entity);

        assertThat(result).isEqualTo(cliente);
        verify(modelMapper).map(entity, Cliente.class);
    }
}
