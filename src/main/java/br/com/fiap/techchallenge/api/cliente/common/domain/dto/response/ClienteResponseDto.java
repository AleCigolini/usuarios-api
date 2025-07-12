package br.com.fiap.techchallenge.api.cliente.common.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ClienteResponseDto {

    @Schema(description = "Identificador único do cliente", example = "e389406d-5531-4acf-a354-be5cc46a8cd4")
    private String id;

    @Schema(description = "Nome do cliente", example = "José da Silva")
    private String nome;

    @Schema(description = "E-mail do cliente", example = "jose.silva@email.com")
    private String email;

    @Schema(description = "CPF do cliente", example = "12022425022")
    private String cpf;

    @Schema(description = "Data de criação do cliente", example = "2023-01-01T10:00:00Z")
    private OffsetDateTime dataCriacao;

    @Schema(description = "Data de atualização do cliente", example = "2023-01-01T10:00:00Z")
    private OffsetDateTime dataAtualizacao;
}