package br.com.fiap.techchallenge.api.cliente.infrastructure.database.repository;

import br.com.fiap.techchallenge.api.cliente.common.domain.entity.JpaClienteEntity;
import br.com.fiap.techchallenge.api.cliente.infrastructure.database.adapter.ClienteJpaAdapter;
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

class ClienteJpaAdapterTest {
    @Mock
    private JpaClienteRepository jpaClienteRepository;

    @InjectMocks
    private ClienteJpaAdapter clienteJpaAdapter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarCliente_quandoEntidadeValida() {
        JpaClienteEntity cliente = new JpaClienteEntity();

        when(jpaClienteRepository.save(cliente)).thenReturn(cliente);

        JpaClienteEntity resultado = clienteJpaAdapter.salvarCliente(cliente);

        assertEquals(cliente, resultado);
        verify(jpaClienteRepository).save(cliente);
    }

    @Test
    void deveBuscarClientePorCpf_quandoCpfExistir() {
        String cpf = "12345678900";
        List<JpaClienteEntity> listaEsperada = List.of(new JpaClienteEntity());

        when(jpaClienteRepository.findByCpf(cpf)).thenReturn(listaEsperada);

        List<JpaClienteEntity> resultado = clienteJpaAdapter.buscarClientePorCpf(cpf);

        assertEquals(listaEsperada, resultado);
        verify(jpaClienteRepository).findByCpf(cpf);
    }

    @Test
    void deveBuscarClientePorId_quandoIdExistir() {
        UUID id = UUID.randomUUID();
        JpaClienteEntity cliente = new JpaClienteEntity();

        when(jpaClienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Optional<JpaClienteEntity> resultado = clienteJpaAdapter.buscarClientePorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(cliente, resultado.get());
        verify(jpaClienteRepository).findById(id);
    }

    @Test
    void deveBuscarClientePorEmail_quandoEmailExistir() {
        String email = "teste@teste.com";
        List<JpaClienteEntity> listaEsperada = List.of(new JpaClienteEntity());

        when(jpaClienteRepository.findByEmail(email)).thenReturn(listaEsperada);

        List<JpaClienteEntity> resultado = clienteJpaAdapter.buscarClientePorEmail(email);

        assertEquals(listaEsperada, resultado);
        verify(jpaClienteRepository).findByEmail(email);
    }

    @Test
    void deveBuscarClientePorEmailECpf_quandoDadosExistirem() {
        String email = "teste@teste.com";
        String cpf = "12345678900";
        List<JpaClienteEntity> listaEsperada = List.of(new JpaClienteEntity());

        when(jpaClienteRepository.findByEmailAndCpf(email, cpf)).thenReturn(listaEsperada);

        List<JpaClienteEntity> resultado = clienteJpaAdapter.buscarClientePorEmailECpf(email, cpf);

        assertEquals(listaEsperada, resultado);
        verify(jpaClienteRepository).findByEmailAndCpf(email, cpf);
    }
}
