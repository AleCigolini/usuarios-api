package br.com.fiap.techchallenge03.cliente.infrastructure.database.adapter;

import br.com.fiap.techchallenge03.cliente.common.domain.entity.JpaClienteEntity;
import br.com.fiap.techchallenge03.cliente.common.interfaces.ClienteDatabase;
import br.com.fiap.techchallenge03.cliente.infrastructure.database.repository.JpaClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ClienteJpaAdapter implements ClienteDatabase {
    private JpaClienteRepository jpaClienteRepository;

    @Override
    public JpaClienteEntity salvarCliente(JpaClienteEntity jpaClienteEntity) {
        return jpaClienteRepository.save(jpaClienteEntity);
    }

    @Override
    public List<JpaClienteEntity> buscarClientePorCpf(String cpf) {
        return jpaClienteRepository.findByCpf(cpf);
    }

    @Override
    public Optional<JpaClienteEntity> buscarClientePorId(UUID id) {
        return jpaClienteRepository.findById(id);
    }

    @Override
    public List<JpaClienteEntity> buscarClientePorEmail(String email) {
        return jpaClienteRepository.findByEmail(email);
    }

    @Override
    public List<JpaClienteEntity> buscarClientePorEmailECpf(String email, String cpf) {
        return jpaClienteRepository.findByEmailAndCpf(email, cpf);
    }

}
