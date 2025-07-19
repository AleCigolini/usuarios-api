package br.com.fiap.techchallenge.api.cliente.infrastructure.authetication.utils;

import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import com.microsoft.graph.models.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GraphApiUtilsTest {

    @Test
    void deveGerarUsuarioAzureAd_quandoClienteComCpf() {
        Cliente cliente = new Cliente();
        cliente.setCpf(new Cpf("89670770092"));
        cliente.setEmail(new Email("teste@teste.com"));

        String tenantName = "tenant.com";

        User user = GraphApiUtils.gerarUsuarioAzureAd(cliente, tenantName);

        assertEquals("89670770092@tenant.com", user.getUserPrincipalName());
        assertEquals("89670770092", user.getDisplayName());
        assertEquals("89670770092", user.getMailNickname());
        assertNotNull(user.getPasswordProfile());
        assertNotEquals(Boolean.TRUE, user.getPasswordProfile().getForceChangePasswordNextSignIn());
        assertTrue(isUUID(user.getPasswordProfile().getPassword()));
        assertEquals(1, user.getIdentities().size());
        assertEquals("emailAddress", user.getIdentities().get(0).getSignInType());
        assertEquals("tenant.com", user.getIdentities().get(0).getIssuer());
        assertEquals("89670770092@tenant.com", user.getIdentities().get(0).getIssuerAssignedId());
        assertNotNull(user.getAdditionalData());
        assertTrue(user.getAdditionalData().containsKey("customSecurityAttributes"));
    }

    @Test
    void deveGerarUsuarioAzureAd_quandoClienteSemCpf() {
        Cliente cliente = new Cliente();
        cliente.setCpf(null);
        cliente.setEmail(new Email("teste@teste.com"));

        String tenantName = "tenant.com";

        User user = GraphApiUtils.gerarUsuarioAzureAd(cliente, tenantName);

        assertEquals("testetestecom@tenant.com", user.getUserPrincipalName());
        assertEquals("teste@teste.com", user.getDisplayName());
        assertEquals("testetestecom", user.getMailNickname());
        assertNotNull(user.getPasswordProfile());
        assertNotEquals(Boolean.TRUE, user.getPasswordProfile().getForceChangePasswordNextSignIn());
        assertTrue(isUUID(user.getPasswordProfile().getPassword()));
        assertEquals(1, user.getIdentities().size());
        assertEquals("emailAddress", user.getIdentities().get(0).getSignInType());
        assertEquals("tenant.com", user.getIdentities().get(0).getIssuer());
        assertEquals("teste@teste.com", user.getIdentities().get(0).getIssuerAssignedId());
        assertNotNull(user.getAdditionalData());
        assertFalse(user.getAdditionalData().containsKey("customSecurityAttributes"));
    }

    @Test
    void deveGerarEmailTenant_quandoClienteComCpf() {
        Cliente cliente = new Cliente();
        cliente.setCpf(new Cpf("89670770092"));
        cliente.setEmail(new Email("teste@teste.com"));

        String tenantName = "tenant.com";

        String resultado = GraphApiUtils.gerarEmailTenant(cliente, tenantName);

        assertEquals("89670770092@tenant.com", resultado);
    }

    @Test
    void deveGerarEmailTenant_quandoClienteSemCpf() {
        Cliente cliente = new Cliente();
        cliente.setCpf(null);
        cliente.setEmail(new Email("teste@teste.com"));

        String tenantName = "tenant.com";

        String resultado = GraphApiUtils.gerarEmailTenant(cliente, tenantName);

        assertEquals("testetestecom@tenant.com", resultado);
    }

    private boolean isUUID(String valor) {
        try {
            UUID.fromString(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
