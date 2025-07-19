package br.com.fiap.techchallenge.api.cliente.common.interfaces;

import br.com.fiap.techchallenge.api.cliente.common.domain.entity.JpaClienteEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteDatabase {
    JpaClienteEntity salvarCliente(JpaClienteEntity cliente);
    List<JpaClienteEntity> buscarClientePorCpf(String cpf);
    Optional<JpaClienteEntity> buscarClientePorId(UUID id);
    List<JpaClienteEntity> buscarClientePorEmail(String email);
    List<JpaClienteEntity> buscarClientePorEmailECpf(String email, String cpf);
}
