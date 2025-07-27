package br.com.fiap.techchallenge.api.usuario.infrastructure.database.repository;

import br.com.fiap.techchallenge.api.usuario.common.domain.entity.JpaUsuarioEntity;
import br.com.fiap.techchallenge.api.usuario.infrastructure.database.adapter.UsuarioJpaAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioJpaAdapterTest {
    @Mock
    private JpaUsuarioRepository jpaUsuarioRepository;

    @InjectMocks
    private UsuarioJpaAdapter usuarioJpaAdapter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarUsuario_quandoEntidadeValida() {
        JpaUsuarioEntity usuario = new JpaUsuarioEntity();

        when(jpaUsuarioRepository.save(usuario)).thenReturn(usuario);

        JpaUsuarioEntity resultado = usuarioJpaAdapter.salvarUsuario(usuario);

        assertEquals(usuario, resultado);
        verify(jpaUsuarioRepository).save(usuario);
    }

    @Test
    void deveBuscarUsuarioPorCpf_quandoCpfExistir() {
        String cpf = "12345678900";
        List<JpaUsuarioEntity> listaEsperada = List.of(new JpaUsuarioEntity());

        when(jpaUsuarioRepository.findByCpf(cpf)).thenReturn(listaEsperada);

        List<JpaUsuarioEntity> resultado = usuarioJpaAdapter.buscarUsuarioPorCpf(cpf);

        assertEquals(listaEsperada, resultado);
        verify(jpaUsuarioRepository).findByCpf(cpf);
    }

    @Test
    void deveBuscarUsuarioPorId_quandoIdExistir() {
        UUID id = UUID.randomUUID();
        JpaUsuarioEntity usuario = new JpaUsuarioEntity();

        when(jpaUsuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Optional<JpaUsuarioEntity> resultado = usuarioJpaAdapter.buscarUsuarioPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(usuario, resultado.get());
        verify(jpaUsuarioRepository).findById(id);
    }

    @Test
    void deveBuscarUsuarioPorEmail_quandoEmailExistir() {
        String email = "teste@teste.com";
        List<JpaUsuarioEntity> listaEsperada = List.of(new JpaUsuarioEntity());

        when(jpaUsuarioRepository.findByEmail(email)).thenReturn(listaEsperada);

        List<JpaUsuarioEntity> resultado = usuarioJpaAdapter.buscarUsuarioPorEmail(email);

        assertEquals(listaEsperada, resultado);
        verify(jpaUsuarioRepository).findByEmail(email);
    }

    @Test
    void deveBuscarUsuarioPorEmailECpf_quandoDadosExistirem() {
        String email = "teste@teste.com";
        String cpf = "12345678900";
        List<JpaUsuarioEntity> listaEsperada = List.of(new JpaUsuarioEntity());

        when(jpaUsuarioRepository.findByEmailAndCpf(email, cpf)).thenReturn(listaEsperada);

        List<JpaUsuarioEntity> resultado = usuarioJpaAdapter.buscarUsuarioPorEmailECpf(email, cpf);

        assertEquals(listaEsperada, resultado);
        verify(jpaUsuarioRepository).findByEmailAndCpf(email, cpf);
    }
}
