package br.com.fiap.techchallenge.api.cliente.infrastructure.authetication.utils;

import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.core.utils.functions.ValidatorUtils;
import com.microsoft.graph.models.ObjectIdentity;
import com.microsoft.graph.models.PasswordProfile;
import com.microsoft.graph.models.User;
import com.microsoft.kiota.serialization.UntypedNode;
import com.microsoft.kiota.serialization.UntypedObject;
import com.microsoft.kiota.serialization.UntypedString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GraphApiUtils {
    public static User gerarUsuarioAzureAd(Cliente cliente, String tenantName) {
        final boolean hasCpf = cliente.getCpf() != null;
        final String userEmail = gerarEmailTenant(cliente, tenantName);

        User newUser = gerarUsuario(cliente, userEmail);

        ObjectIdentity identity = new ObjectIdentity();
        identity.setIssuer(tenantName);
        identity.setSignInType("emailAddress");
        identity.setIssuerAssignedId(cliente.getCpf() != null ? userEmail : cliente.getEmail().getEndereco());

        if (hasCpf) {
            adicionarCpfUsuario(cliente, newUser);
        }

        newUser.setIdentities(Collections.singletonList(identity));
        gerarSenhaUsuario(newUser);
        return newUser;
    }

    public static String gerarEmailTenant(Cliente cliente, String tenantName) {
        String identificadorEmail = buscarCpfOuEmail(cliente).replaceAll("[^a-zA-Z0-9]", "");
        return identificadorEmail + "@" + tenantName;
    }

    private static String buscarCpfOuEmail(Cliente cliente) {
        if (cliente.getCpf() != null && ValidatorUtils.isNotBlank(cliente.getCpf().getValue())) {
            return cliente.getCpf().getValue();
        }
        return cliente.getEmail().getEndereco();
    }

    private static User gerarUsuario(Cliente cliente, String userEmail) {
        User newUser = new User();
        newUser.setAccountEnabled(true);
        newUser.setDisplayName(buscarCpfOuEmail(cliente));
        newUser.setMailNickname(userEmail.split("@")[0]);
        newUser.setUserPrincipalName(userEmail);
        return newUser;
    }

    private static void gerarSenhaUsuario(User newUser) {
        PasswordProfile passwordProfile = new PasswordProfile();
        passwordProfile.setPassword(UUID.randomUUID().toString());
        passwordProfile.setForceChangePasswordNextSignIn(false);
        newUser.setPasswordProfile(passwordProfile);
    }

    private static void adicionarCpfUsuario(Cliente cliente, User newUser) {
        Map<String, UntypedNode> setMap = new HashMap<>();
        Map<String, UntypedNode> valueMap = new HashMap<>();
        valueMap.put("@odata.type", new UntypedString("#Microsoft.DirectoryServices.CustomSecurityAttributeValue"));
        valueMap.put("CPF", new UntypedString(cliente.getCpf().getValue()));

        setMap.put("DadosUsuario", new UntypedObject(valueMap));

        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("customSecurityAttributes", new UntypedObject(setMap));

        newUser.setAdditionalData(additionalData);
    }
}
