package br.com.fiap.techchallenge.api.role.common.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleRequestDto {
    @Schema(description = "Nome da permissão", example = "ADMIN")
    private String role;

    @Schema(description = "Descrição da permissão", example = "Permissão de administrador")
    private String descricao;
}
