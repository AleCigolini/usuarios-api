package br.com.fiap.techchallenge.api.cliente.infrastructure.authetication.adapter;

import br.com.fiap.techchallenge.api.cliente.common.interfaces.ClienteAuthentication;
import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.cliente.infrastructure.authetication.utils.GraphApiUtils;
import com.microsoft.graph.models.User;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraphApiAdapter implements ClienteAuthentication {
    private final GraphServiceClient graphServiceClient;

    @Value("${azure.entra-id.tenant-name}")
    private String tenantName;

    @Override
    public void salvarClienteAuthentication(Cliente cliente) {
        graphServiceClient.users().post(GraphApiUtils.gerarUsuarioAzureAd(cliente, tenantName));
    }

    @Override
    public boolean isClienteExistente(Cliente cliente) {
        try {
            String userEmail = GraphApiUtils.gerarEmailTenant(cliente, tenantName);
            User user = graphServiceClient.users().byUserId(userEmail).get();
            return user != null;
        } catch (Exception e) {
            return false;
        }
    }

}
