package br.com.fiap.techchallenge.api.role.common.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class RoleResponseDto {
    @Schema(description = "Nome da permissão", example = "ADMIN")
    private String role;

    @Schema(description = "Descrição da permissão", example = "Permissão de administrador")
    private String descricao;

    @Schema(description = "Indica se a role está ativa", example = "true")
    private Boolean ativo;

    @Schema(description = "Data de criação da role", example = "2023-01-01T10:00:00Z")
    private OffsetDateTime dataCriacao;

    @Schema(description = "Data de atualização da role", example = "2023-01-01T10:00:00Z")
    private OffsetDateTime dataAtualizacao;
}