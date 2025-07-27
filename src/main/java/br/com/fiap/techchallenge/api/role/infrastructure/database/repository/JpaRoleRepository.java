package br.com.fiap.techchallenge.api.role.infrastructure.database.repository;


import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<JpaRoleEntity, String> {
    List<JpaRoleEntity> findByRoleAndAtivo(String role, Boolean ativo);
    Optional<JpaRoleEntity> findByRole(String role);
}
