package br.com.fiap.techchallenge.api.cliente.presentation.validator;

import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.ClienteRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteEmailOuCpfValidator implements ConstraintValidator<EmailOuCpf, ClienteRequestDto> {
    @Override
    public boolean isValid(ClienteRequestDto clienteRequestDto, ConstraintValidatorContext context) {
        return clienteRequestDto != null &&
                ((clienteRequestDto.getEmail() != null && !clienteRequestDto.getEmail().isBlank()) ||
                        (clienteRequestDto.getCpf() != null && !clienteRequestDto.getCpf().isBlank()));
    }
}
