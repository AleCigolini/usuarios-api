package br.com.fiap.techchallenge.api.usuario.common.domain.dto.request;

import br.com.fiap.techchallenge.api.core.utils.validators.cpf.Cpf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdentificarUsuarioDto {
    @Cpf
    @NotBlank
    @NotNull
    private String cpf;
}
