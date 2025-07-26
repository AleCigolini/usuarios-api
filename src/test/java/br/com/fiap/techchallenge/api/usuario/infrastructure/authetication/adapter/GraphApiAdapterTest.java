package br.com.fiap.techchallenge.api.usuario.infrastructure.authetication.adapter;

import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.usuario.infrastructure.authetication.utils.GraphApiUtils;
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
    void deveSalvarUsuarioAuthentication() {
        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            Usuario usuario = new Usuario();
            User userMock = new User();
            utilsMock.when(() -> GraphApiUtils.gerarUsuarioAzureAd(usuario, "meu-tenant"))
                    .thenReturn(userMock);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.post(userMock)).thenReturn(userMock);

            graphApiAdapter.salvarUsuarioAuthentication(usuario);

            utilsMock.verify(() -> GraphApiUtils.gerarUsuarioAzureAd(usuario, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).post(userMock);
        }
    }

    @Test
    void deveMapearParaBuscarUsuarioPorCpf_quandoUsuarioExistir() {
        Usuario usuario = new Usuario();
        String emailEsperado = "usuario@meu-tenant";

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            utilsMock.when(() -> GraphApiUtils.gerarEmailTenant(usuario, "meu-tenant"))
                    .thenReturn(emailEsperado);

            UserItemRequestBuilder userItemRequestBuilder = mock(UserItemRequestBuilder.class);
            when(userItemRequestBuilder.get()).thenReturn(mock(User.class));

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.byUserId(emailEsperado)).thenReturn(userItemRequestBuilder);

            boolean existe = graphApiAdapter.isUsuarioExistente(usuario);

            assertTrue(existe);
            utilsMock.verify(() -> GraphApiUtils.gerarEmailTenant(usuario, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).byUserId(emailEsperado);
        }
    }

    @Test
    void naoDeveMapearParaBuscarUsuarioPorCpf_quandoUsuarioNaoExistir() {
        Usuario usuario = new Usuario();
        String emailEsperado = "usuario@meu-tenant";

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            utilsMock.when(() -> GraphApiUtils.gerarEmailTenant(usuario, "meu-tenant"))
                    .thenReturn(emailEsperado);

            UserItemRequestBuilder userItemRequestBuilder = mock(UserItemRequestBuilder.class);
            when(userItemRequestBuilder.get()).thenReturn(null);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.byUserId(emailEsperado)).thenReturn(userItemRequestBuilder);

            boolean existe = graphApiAdapter.isUsuarioExistente(usuario);

            assertFalse(existe);
            utilsMock.verify(() -> GraphApiUtils.gerarEmailTenant(usuario, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).byUserId(emailEsperado);
        }
    }

    @Test
    void naoDeveMapearParaBuscarUsuarioPorCpf_quandoOcorrerExcecao() {
        Usuario usuario = new Usuario();
        String emailEsperado = "usuario@meu-tenant";

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            utilsMock.when(() -> GraphApiUtils.gerarEmailTenant(usuario, "meu-tenant"))
                    .thenReturn(emailEsperado);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.byUserId(emailEsperado)).thenReturn(null);
            when(userRequestBuilder.get()).thenThrow(new RuntimeException("erro"));

            boolean existe = graphApiAdapter.isUsuarioExistente(usuario);

            assertFalse(existe);
            utilsMock.verify(() -> GraphApiUtils.gerarEmailTenant(usuario, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).byUserId(emailEsperado);
        }
    }

    @Test
    void deveSalvarUsuarioAuthentication_quandoUsuarioValido() {
        Usuario usuario = new Usuario();

        try (MockedStatic<GraphApiUtils> utilsMock = mockStatic(GraphApiUtils.class)) {
            User userMock = new User();
            utilsMock.when(() -> GraphApiUtils.gerarUsuarioAzureAd(usuario, "meu-tenant"))
                    .thenReturn(userMock);

            when(graphServiceClient.users()).thenReturn(userRequestBuilder);
            when(userRequestBuilder.post(any())).thenReturn(userMock);

            graphApiAdapter.salvarUsuarioAuthentication(usuario);

            utilsMock.verify(() -> GraphApiUtils.gerarUsuarioAzureAd(usuario, "meu-tenant"));
            verify(graphServiceClient).users();
            verify(userRequestBuilder).post(userMock);
        }
    }
}