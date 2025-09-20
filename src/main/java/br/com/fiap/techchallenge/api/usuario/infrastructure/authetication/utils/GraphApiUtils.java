package br.com.fiap.techchallenge.api.usuario.infrastructure.authetication.utils;

import br.com.fiap.techchallenge.api.core.utils.functions.ValidatorUtils;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import com.microsoft.graph.models.ObjectIdentity;
import com.microsoft.graph.models.PasswordProfile;
import com.microsoft.graph.models.User;
import com.microsoft.kiota.serialization.UntypedNode;
import com.microsoft.kiota.serialization.UntypedObject;
import com.microsoft.kiota.serialization.UntypedString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class GraphApiUtils {
    public static User gerarUsuarioAzureAd(Usuario usuario, String tenantName) {
        final boolean hasCpf = usuario.getCpf() != null;
        final String userEmail = gerarEmailTenant(usuario, tenantName);

        User newUser = gerarUsuario(usuario, userEmail);

        ObjectIdentity identity = new ObjectIdentity();
        identity.setIssuer(tenantName);
        identity.setSignInType("emailAddress");
        identity.setIssuerAssignedId(usuario.getCpf() != null ? userEmail : usuario.getEmail().getEndereco());

        if (hasCpf) {
            adicionarCpfUsuario(usuario, newUser);
        }

        newUser.setIdentities(Collections.singletonList(identity));
        return newUser;
    }

    public static String gerarEmailTenant(Usuario usuario, String tenantName) {
        String identificadorEmail = buscarCpfOuEmail(usuario).replaceAll("[^a-zA-Z0-9]", "");
        return identificadorEmail + "@" + tenantName;
    }

    private static String buscarCpfOuEmail(Usuario usuario) {
        if (usuario.getCpf() != null && ValidatorUtils.isNotBlank(usuario.getCpf().getValue())) {
            return usuario.getCpf().getValue();
        }
        return usuario.getEmail().getEndereco();
    }

    private static User gerarUsuario(Usuario usuario, String userEmail) {
        User newUser = new User();
        newUser.setAccountEnabled(true);
        newUser.setDisplayName(buscarCpfOuEmail(usuario));
        newUser.setMailNickname(userEmail.split("@")[0]);
        newUser.setUserPrincipalName(userEmail);
        newUser.setPasswordProfile(montarSenhaUsuario(usuario.getSenha()));
        return newUser;
    }

    private static PasswordProfile montarSenhaUsuario(String senhaUsuario) {
        PasswordProfile passwordProfile = new PasswordProfile();
        passwordProfile.setPassword(senhaUsuario);
        passwordProfile.setForceChangePasswordNextSignIn(false);

        return passwordProfile;
    }

    private static void adicionarCpfUsuario(Usuario usuario, User newUser) {
        Map<String, UntypedNode> setMap = new HashMap<>();
        Map<String, UntypedNode> valueMap = new HashMap<>();
        valueMap.put("@odata.type", new UntypedString("#Microsoft.DirectoryServices.CustomSecurityAttributeValue"));
        valueMap.put("CPF", new UntypedString(usuario.getCpf().getValue()));

        setMap.put("DadosUsuario", new UntypedObject(valueMap));

        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("customSecurityAttributes", new UntypedObject(setMap));

        newUser.setAdditionalData(additionalData);
    }
}
