package br.com.fiap.techchallenge.api.usuario.infrastructure.authetication.utils;

import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import com.microsoft.graph.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphApiUtilsTest {

    @Test
    void deveGerarUsuarioAzureAd_quandoUsuarioComCpf() {
        Usuario usuario = new Usuario();
        usuario.setCpf(new Cpf("89670770092"));
        usuario.setEmail(new Email("teste@teste.com"));
        usuario.setLogin("teste");
        usuario.setSenha("encoded");

        String tenantName = "tenant.com";

        User user = GraphApiUtils.gerarUsuarioAzureAd(usuario, tenantName);

        assertEquals("89670770092@tenant.com", user.getUserPrincipalName());
        assertEquals("89670770092", user.getDisplayName());
        assertEquals("89670770092", user.getMailNickname());
        assertNotNull(user.getPasswordProfile());
        assertNotEquals(Boolean.TRUE, user.getPasswordProfile().getForceChangePasswordNextSignIn());
        assertNotNull(user.getPasswordProfile().getPassword());
        assertEquals(1, user.getIdentities().size());
        assertEquals("emailAddress", user.getIdentities().get(0).getSignInType());
        assertEquals("tenant.com", user.getIdentities().get(0).getIssuer());
        assertEquals("89670770092@tenant.com", user.getIdentities().get(0).getIssuerAssignedId());
        assertNotNull(user.getAdditionalData());
        assertTrue(user.getAdditionalData().containsKey("customSecurityAttributes"));
    }

    @Test
    void deveGerarUsuarioAzureAd_quandoUsuarioSemCpf() {
        Usuario usuario = new Usuario();
        usuario.setCpf(null);
        usuario.setEmail(new Email("teste@teste.com"));
        usuario.setLogin("teste");
        usuario.setSenha("encoded");

        String tenantName = "tenant.com";

        User user = GraphApiUtils.gerarUsuarioAzureAd(usuario, tenantName);

        assertEquals("testetestecom@tenant.com", user.getUserPrincipalName());
        assertEquals("teste@teste.com", user.getDisplayName());
        assertEquals("testetestecom", user.getMailNickname());
        assertNotNull(user.getPasswordProfile());
        assertNotEquals(Boolean.TRUE, user.getPasswordProfile().getForceChangePasswordNextSignIn());
        assertNotNull(user.getPasswordProfile().getPassword());
        assertEquals(1, user.getIdentities().size());
        assertEquals("emailAddress", user.getIdentities().get(0).getSignInType());
        assertEquals("tenant.com", user.getIdentities().get(0).getIssuer());
        assertEquals("teste@teste.com", user.getIdentities().get(0).getIssuerAssignedId());
        assertNotNull(user.getAdditionalData());
        assertFalse(user.getAdditionalData().containsKey("customSecurityAttributes"));
    }

    @Test
    void deveGerarEmailTenant_quandoUsuarioComCpf() {
        Usuario usuario = new Usuario();
        usuario.setCpf(new Cpf("89670770092"));
        usuario.setEmail(new Email("teste@teste.com"));

        String tenantName = "tenant.com";

        String resultado = GraphApiUtils.gerarEmailTenant(usuario, tenantName);

        assertEquals("89670770092@tenant.com", resultado);
    }

    @Test
    void deveGerarEmailTenant_quandoUsuarioSemCpf() {
        Usuario usuario = new Usuario();
        usuario.setCpf(null);
        usuario.setEmail(new Email("teste@teste.com"));

        String tenantName = "tenant.com";

        String resultado = GraphApiUtils.gerarEmailTenant(usuario, tenantName);

        assertEquals("testetestecom@tenant.com", resultado);
    }
}
