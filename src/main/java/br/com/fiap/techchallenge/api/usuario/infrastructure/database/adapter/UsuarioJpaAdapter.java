package br.com.fiap.techchallenge.api.usuario.infrastructure.database.adapter;

import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioDatabase;
import br.com.fiap.techchallenge.api.usuario.infrastructure.database.repository.JpaUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UsuarioJpaAdapter implements UsuarioDatabase {
    private JpaUsuarioRepository jpaUsuarioRepository;

    @Override
    public JpaUsuarioEntity salvarUsuario(JpaUsuarioEntity jpaUsuarioEntity) {
        return jpaUsuarioRepository.save(jpaUsuarioEntity);
    }

    @Override
    public List<JpaUsuarioEntity> buscarUsuarioPorCpf(String cpf) {
        return jpaUsuarioRepository.findByCpf(cpf);
    }

    @Override
    public Optional<JpaUsuarioEntity> buscarUsuarioPorId(UUID id) {
        return jpaUsuarioRepository.findById(id);
    }

    @Override
    public List<JpaUsuarioEntity> buscarUsuarioPorEmail(String email) {
        return jpaUsuarioRepository.findByEmail(email);
    }

    @Override
    public List<JpaUsuarioEntity> buscarUsuarioPorEmailECpf(String email, String cpf) {
        return jpaUsuarioRepository.findByEmailAndCpf(email, cpf);
    }

}
