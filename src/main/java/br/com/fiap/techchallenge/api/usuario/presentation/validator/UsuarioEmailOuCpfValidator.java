package br.com.fiap.techchallenge.api.usuario.presentation.validator;

import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsuarioEmailOuCpfValidator implements ConstraintValidator<EmailOuCpf, UsuarioRequestDto> {
    @Override
    public boolean isValid(UsuarioRequestDto usuarioRequestDto, ConstraintValidatorContext context) {
        return usuarioRequestDto != null &&
                ((usuarioRequestDto.getEmail() != null && !usuarioRequestDto.getEmail().isBlank()) ||
                        (usuarioRequestDto.getCpf() != null && !usuarioRequestDto.getCpf().isBlank()));
    }
}
