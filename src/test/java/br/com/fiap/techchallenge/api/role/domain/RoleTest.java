package br.com.fiap.techchallenge.api.role.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void deveSetarEObterRole() {
        Role role = new Role();
        role.setRole("ADMIN");
        assertEquals("ADMIN", role.getRole());
    }

    @Test
    void deveSetarEObterDescricao() {
        Role role = new Role();
        role.setDescricao("Administrador do sistema");
        assertEquals("Administrador do sistema", role.getDescricao());
    }

    @Test
    void deveSetarEObterAtivoTrue() {
        Role role = new Role();
        role.setAtivo(true);
        assertTrue(role.getAtivo());
    }

    @Test
    void deveSetarEObterAtivoFalse() {
        Role role = new Role();
        role.setAtivo(false);
        assertFalse(role.getAtivo());
    }
}
