package br.com.fiap.techchallenge.api.usuario.infrastructure.authetication.adapter;

import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioAuthentication;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.usuario.infrastructure.authetication.utils.GraphApiUtils;
import com.microsoft.graph.models.User;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphApiAdapter implements UsuarioAuthentication {
    private final GraphServiceClient graphServiceClient;

    @Value("${azure.entra-id.tenant-name}")
    private String tenantName;

    @Override
    public void salvarUsuarioAuthentication(Usuario usuario) {
        graphServiceClient.users().post(GraphApiUtils.gerarUsuarioAzureAd(usuario, tenantName));
    }

    @Override
    public boolean isUsuarioExistente(Usuario usuario) {
        try {
            String userEmail = GraphApiUtils.gerarEmailTenant(usuario, tenantName);
            User user = graphServiceClient.users().byUserId(userEmail).get();
            return user != null;
        } catch (Exception e) {
            return false;
        }
    }

}
