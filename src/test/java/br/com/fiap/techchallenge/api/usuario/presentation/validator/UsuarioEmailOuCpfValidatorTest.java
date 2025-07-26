package br.com.fiap.techchallenge.api.usuario.presentation.validator;

import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioEmailOuCpfValidatorTest {
    private UsuarioEmailOuCpfValidator validator;

    @BeforeEach
    void setup() {
        validator = new UsuarioEmailOuCpfValidator();
    }

    @Test
    void deveValidarUsuario_quandoCpfInformado() {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setCpf("12345678900");
        dto.setEmail(null);

        boolean valido = validator.isValid(dto, null);

        assertTrue(valido);
    }

    @Test
    void deveValidarUsuario_quandoEmailInformado() {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setCpf(null);
        dto.setEmail("teste@teste.com");

        boolean valido = validator.isValid(dto, null);

        assertTrue(valido);
    }

    @Test
    void deveValidarUsuario_quandoCpfEEmailInformados() {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setCpf("12345678900");
        dto.setEmail("teste@teste.com");

        boolean valido = validator.isValid(dto, null);

        assertTrue(valido);
    }

    @Test
    void naoDeveValidarUsuario_quandoCpfEEmailNulos() {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setCpf(null);
        dto.setEmail(null);

        boolean valido = validator.isValid(dto, null);

        assertFalse(valido);
    }

    @Test
    void naoDeveValidarUsuario_quandoCpfEEmailVazios() {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setCpf("");
        dto.setEmail("");

        boolean valido = validator.isValid(dto, null);

        assertFalse(valido);
    }

    @Test
    void naoDeveValidarUsuario_quandoRequestDtoForNulo() {
        boolean valido = validator.isValid(null, null);

        assertFalse(valido);
    }
}
