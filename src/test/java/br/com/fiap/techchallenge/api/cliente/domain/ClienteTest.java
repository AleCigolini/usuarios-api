package br.com.fiap.techchallenge.api.cliente.domain;

import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClienteTest {

    private Cliente cliente;
    private Cpf cpf;
    private Email email;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cpf = new Cpf("89670770092");
        email = new Email("test@example.com");
    }

    @Test
    void deveDefinirERetornarId() {
        cliente.setId("123");
        assertEquals("123", cliente.getId());
    }

    @Test
    void deveDefinirERetornarNome() {
        cliente.setNome("João Silva");
        assertEquals("João Silva", cliente.getNome());
    }

    @Test
    void deveDefinirERetornarEmail() {
        cliente.setEmail(email);
        assertEquals(email, cliente.getEmail());
    }

    @Test
    void deveDefinirERetornarCpf() {
        cliente.setCpf(cpf);
        assertEquals(cpf, cliente.getCpf());
    }

    @Test
    void deveDefinirERetornarIdNulo() {
        cliente.setId(null);
        assertNull(cliente.getId());
    }

    @Test
    void deveDefinirERetornarNomeNulo() {
        cliente.setNome(null);
        assertNull(cliente.getNome());
    }

    @Test
    void deveDefinirERetornarEmailNulo() {
        cliente.setEmail(null);
        assertNull(cliente.getEmail());
    }

    @Test
    void deveDefinirERetornarCpfNulo() {
        cliente.setCpf(null);
        assertNull(cliente.getCpf());
    }

}
