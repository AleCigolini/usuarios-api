package br.com.fiap.techchallenge.api.usuario.common.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UsuarioResponseDto {

    @Schema(description = "Identificador único do usuario", example = "e389406d-5531-4acf-a354-be5cc46a8cd4")
    private String id;

    @Schema(description = "Nome do usuario", example = "José da Silva")
    private String nome;

    @Schema(description = "E-mail do usuario", example = "jose.silva@email.com")
    private String email;

    @Schema(description = "CPF do usuario", example = "12022425022")
    private String cpf;

    @Schema(description = "Login do usuario", example = "teste123")
    private String login;

    @Schema(description = "Senha do usuario", example = "$2a$12$rgMPW8nNj5zUyfcgFSq8qOVqVsNuenUXPmxj0BqyD8BDvt.saEgPK")
    private String senha;

    @Schema(description = "Data de criação do usuario", example = "2023-01-01T10:00:00Z")
    private OffsetDateTime dataCriacao;

    @Schema(description = "Data de atualização do usuario", example = "2023-01-01T10:00:00Z")
    private OffsetDateTime dataAtualizacao;

    @Schema(description = "Role do usuário", example = "USUARIO")
    private String role;
}