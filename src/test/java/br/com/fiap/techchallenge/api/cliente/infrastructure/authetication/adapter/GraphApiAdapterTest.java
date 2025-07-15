package br.com.fiap.techchallenge.api.cliente.infrastructure.authetication.adapter;

import br.com.fiap.techchallenge.api.cliente.domain.Cliente;
import br.com.fiap.techchallenge.api.cliente.infrastructure.authetication.utils.GraphApiUtils;
import com.microsoft.graph.models.User;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.graph.users.UsersRequestBuilder;
import com.microsoft.graph.users.item.UserItemRequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GraphApiAdapterTest {

    @Mock
    private GraphServiceClient graphServiceClient;

    @Mock
    private UsersRequestBuilder userRequestBuilder;

    @InjectMocks
    private GraphApiAdapter graphApiAdapter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(graphApiAdapter, "tenantName", "meu-tenant");
    }

    @Test
    void deveSalvarClienteAuthentication() {
        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            Cliente cliente = new Cliente();
            User userMock = new User();
            utilsMock.when(() -> GraphApiUtils.gerarUsuarioAzureAd(cliente, "meu-tenant"))
                    .thenReturn(userMock);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.post(userMock)).thenReturn(userMock);

            graphApiAdapter.salvarClienteAuthentication(cliente);

            utilsMock.verify(() -> GraphApiUtils.gerarUsuarioAzureAd(cliente, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).post(userMock);
        }
    }

    @Test
    void deveMapearParaBuscarClientePorCpf_quandoClienteExistir() {
        Cliente cliente = new Cliente();
        String emailEsperado = "cliente@meu-tenant";

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            utilsMock.when(() -> GraphApiUtils.gerarEmailTenant(cliente, "meu-tenant"))
                    .thenReturn(emailEsperado);

            UserItemRequestBuilder userItemRequestBuilder = mock(UserItemRequestBuilder.class);
            when(userItemRequestBuilder.get()).thenReturn(mock(User.class));

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.byUserId(emailEsperado)).thenReturn(userItemRequestBuilder);

            boolean existe = graphApiAdapter.isClienteExistente(cliente);

            assertTrue(existe);
            utilsMock.verify(() -> GraphApiUtils.gerarEmailTenant(cliente, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).byUserId(emailEsperado);
        }
    }

    @Test
    void naoDeveMapearParaBuscarClientePorCpf_quandoClienteNaoExistir() {
        Cliente cliente = new Cliente();
        String emailEsperado = "cliente@meu-tenant";

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            utilsMock.when(() -> GraphApiUtils.gerarEmailTenant(cliente, "meu-tenant"))
                    .thenReturn(emailEsperado);

            UserItemRequestBuilder userItemRequestBuilder = mock(UserItemRequestBuilder.class);
            when(userItemRequestBuilder.get()).thenReturn(null);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.byUserId(emailEsperado)).thenReturn(userItemRequestBuilder);

            boolean existe = graphApiAdapter.isClienteExistente(cliente);

            assertFalse(existe);
            utilsMock.verify(() -> GraphApiUtils.gerarEmailTenant(cliente, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).byUserId(emailEsperado);
        }
    }

    @Test
    void naoDeveMapearParaBuscarClientePorCpf_quandoOcorrerExcecao() {
        Cliente cliente = new Cliente();
        String emailEsperado = "cliente@meu-tenant";

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            utilsMock.when(() -> GraphApiUtils.gerarEmailTenant(cliente, "meu-tenant"))
                    .thenReturn(emailEsperado);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.byUserId(emailEsperado)).thenReturn(null);
            when(userRequestBuilder.get()).thenThrow(new RuntimeException("erro"));

            boolean existe = graphApiAdapter.isClienteExistente(cliente);

            assertFalse(existe);
            utilsMock.verify(() -> GraphApiUtils.gerarEmailTenant(cliente, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).byUserId(emailEsperado);
        }
    }

    @Test
    void deveSalvarClienteAuthentication_quandoClienteValido() {
        Cliente cliente = new Cliente();

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            User userMock = new User();
            utilsMock.when(() -> GraphApiUtils.gerarUsuarioAzureAd(cliente, "meu-tenant"))
                    .thenReturn(userMock);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.post(any())).thenReturn(userMock);

            graphApiAdapter.salvarClienteAuthentication(cliente);

            utilsMock.verify(() -> GraphApiUtils.gerarUsuarioAzureAd(cliente, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).post(userMock);
        }
    }
}