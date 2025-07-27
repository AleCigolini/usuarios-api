package br.com.fiap.techchallenge.api.usuario.common.domain.entity;

import br.com.fiap.techchallenge.api.core.utils.entity.JpaBaseEntity;
import br.com.fiap.techchallenge.api.role.common.domain.entity.JpaRoleEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class JpaUsuarioEntity extends JpaBaseEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true, length = 11)
    private String cpf;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private JpaRoleEntity jpaRoleEntity;

}
