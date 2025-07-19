package br.com.fiap.techchallenge.api.cliente.presentation.validator;

import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.ClienteRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClienteEmailOuCpfValidatorTest {
    private ClienteEmailOuCpfValidator validator;

    @BeforeEach
    void setup() {
        validator = new ClienteEmailOuCpfValidator();
    }

    @Test
    void deveValidarCliente_quandoCpfInformado() {
        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setCpf("12345678900");
        dto.setEmail(null);

        boolean valido = validator.isValid(dto, null);

        assertTrue(valido);
    }

    @Test
    void deveValidarCliente_quandoEmailInformado() {
        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setCpf(null);
        dto.setEmail("teste@teste.com");

        boolean valido = validator.isValid(dto, null);

        assertTrue(valido);
    }

    @Test
    void deveValidarCliente_quandoCpfEEmailInformados() {
        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setCpf("12345678900");
        dto.setEmail("teste@teste.com");

        boolean valido = validator.isValid(dto, null);

        assertTrue(valido);
    }

    @Test
    void naoDeveValidarCliente_quandoCpfEEmailNulos() {
        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setCpf(null);
        dto.setEmail(null);

        boolean valido = validator.isValid(dto, null);

        assertFalse(valido);
    }

    @Test
    void naoDeveValidarCliente_quandoCpfEEmailVazios() {
        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setCpf("");
        dto.setEmail("");

        boolean valido = validator.isValid(dto, null);

        assertFalse(valido);
    }

    @Test
    void naoDeveValidarCliente_quandoRequestDtoForNulo() {
        boolean valido = validator.isValid(null, null);

        assertFalse(valido);
    }
}
