package br.com.fiap.techchallenge.api.role.infrastructure.database.adapter;

import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import br.com.fiap.techchallenge.api.role.common.interfaces.RoleDatabase;
import br.com.fiap.techchallenge.api.role.infrastructure.database.repository.JpaRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleJpaAdapter implements RoleDatabase {
    private JpaRoleRepository jpaRoleRepository;

    @Override
    public JpaRoleEntity salvarRole(JpaRoleEntity role) {
        return jpaRoleRepository.save(role);
    }

    @Override
    public List<JpaRoleEntity> buscarRoles() {
        return jpaRoleRepository.findAll();
    }

    @Override
    public Optional<JpaRoleEntity> buscarRolePorNome(String role) {
        return jpaRoleRepository.findByRole(role);
    }

}
