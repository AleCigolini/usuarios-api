package br.com.fiap.techchallenge.api.cliente.infrastructure.database.repository;


import br.com.fiap.techchallenge.api.cliente.common.domain.entity.JpaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaClienteRepository extends JpaRepository<JpaClienteEntity, UUID> {
    List<JpaClienteEntity> findByCpf(String cpf);
    List<JpaClienteEntity> findByEmail(String email);
    List<JpaClienteEntity> findByEmailAndCpf(String email, String cpf);
}
