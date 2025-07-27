package br.com.fiap.techchallenge.api.usuario.infrastructure.database.repository;


import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, UUID> {
    List<JpaUsuarioEntity> findByCpf(String cpf);
    List<JpaUsuarioEntity> findByEmail(String email);
    List<JpaUsuarioEntity> findByEmailAndCpf(String email, String cpf);
}
