package br.com.fiap.techchallenge.api.cliente.common.domain.dto.request;

import br.com.fiap.techchallenge.api.core.utils.validators.cpf.Cpf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdentificarClienteDto {
    @Cpf
    @NotBlank
    @NotNull
    private String cpf;
}
