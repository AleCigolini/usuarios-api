package br.com.fiap.techchallenge.api.cliente.application.mapper;

import br.com.fiap.techchallenge.api.cliente.common.domain.entity.JpaClienteEntity;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;

public interface DatabaseClienteMapper {
    JpaClienteEntity toJpaClienteEntity(Cliente usuario);
    Cliente toCliente(JpaClienteEntity jpaClienteEntity);
}