package br.com.fiap.techchallenge03.cliente.common.domain.dto.request;

import br.com.fiap.techchallenge03.core.utils.validators.cpf.Cpf;
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
