package br.com.fiap.techchallenge.api.usuario.common.domain.dto.request;

import br.com.fiap.techchallenge.api.usuario.presentation.validator.EmailOuCpf;
import br.com.fiap.techchallenge.api.core.utils.validators.cpf.Cpf;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@EmailOuCpf
public class UsuarioRequestDto {
    @Size(max = 255, message = "O nome deve ter {max} caracteres")
    private String nome;
    @Size(max = 255, message = "O e-mail deve ter {max} caracteres")
    private String email;
    @Cpf
    private String cpf;
}

