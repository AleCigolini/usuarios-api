package br.com.fiap.techchallenge03.cliente.application.mapper;

import br.com.fiap.techchallenge03.cliente.common.domain.entity.JpaClienteEntity;
import br.com.fiap.techchallenge03.cliente.domain.Cliente;

public interface DatabaseClienteMapper {
    JpaClienteEntity toJpaClienteEntity(Cliente usuario);
    Cliente toCliente(JpaClienteEntity jpaClienteEntity);
}