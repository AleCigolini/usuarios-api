package br.com.fiap.techchallenge.api.role.common.domain.entity;

import br.com.fiap.techchallenge.api.core.utils.entity.JpaBaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class JpaRoleEntity extends JpaBaseEntity {
    @Id
    @Column(length = 100)
    @EqualsAndHashCode.Include
    private String role;

    @Column(length = 100)
    private String descricao;

    @Column(nullable = false)
    private Boolean ativo;

}
