package br.com.fiap.techchallenge03.cliente.application.mapper.modelmapper;

import br.com.fiap.techchallenge03.cliente.application.mapper.DatabaseClienteMapper;
import br.com.fiap.techchallenge03.cliente.common.domain.entity.JpaClienteEntity;
import br.com.fiap.techchallenge03.cliente.domain.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseClienteModelMapper implements DatabaseClienteMapper {
    private ModelMapper modelMapper;

    public JpaClienteEntity toJpaClienteEntity(Cliente usuario) {
        return modelMapper.map(usuario, JpaClienteEntity.class);
    }

    public Cliente toCliente(JpaClienteEntity jpaClienteEntity) {
        return modelMapper.map(jpaClienteEntity, Cliente.class);
    }
}