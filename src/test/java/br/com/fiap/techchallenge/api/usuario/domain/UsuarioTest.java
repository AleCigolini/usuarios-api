package br.com.fiap.techchallenge.api.usuario.domain;

import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UsuarioTest {

    private Usuario usuario;
    private Cpf cpf;
    private Email email;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        cpf = new Cpf("89670770092");
        email = new Email("test@example.com");
    }

    @Test
    void deveDefinirERetornarId() {
        usuario.setId("123");
        assertEquals("123", usuario.getId());
    }

    @Test
    void deveDefinirERetornarNome() {
        usuario.setNome("João Silva");
        assertEquals("João Silva", usuario.getNome());
    }

    @Test
    void deveDefinirERetornarEmail() {
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail());
    }

    @Test
    void deveDefinirERetornarCpf() {
        usuario.setCpf(cpf);
        assertEquals(cpf, usuario.getCpf());
    }

    @Test
    void deveDefinirERetornarIdNulo() {
        usuario.setId(null);
        assertNull(usuario.getId());
    }

    @Test
    void deveDefinirERetornarNomeNulo() {
        usuario.setNome(null);
        assertNull(usuario.getNome());
    }

    @Test
    void deveDefinirERetornarEmailNulo() {
        usuario.setEmail(null);
        assertNull(usuario.getEmail());
    }

    @Test
    void deveDefinirERetornarCpfNulo() {
        usuario.setCpf(null);
        assertNull(usuario.getCpf());
    }

}
