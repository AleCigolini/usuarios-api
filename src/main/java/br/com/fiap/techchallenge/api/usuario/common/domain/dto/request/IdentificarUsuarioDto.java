package br.com.fiap.techchallenge.api.usuario.common.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdentificarUsuarioDto {
    @NotBlank
    @NotNull
    private String login;

    @NotBlank
    @NotNull
    private String senha;
}
