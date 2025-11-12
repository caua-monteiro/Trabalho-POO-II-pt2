package com.appacademia.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setCpf("12345678900");
        usuario.setEmail("carlos@email.com");
        usuario.setDataNascimento(LocalDate.of(1990, 1, 1));
        usuario.setTipoUsuario("cliente");
    }

    @Test
    void deveCriarUsuarioComDadosCorretos() {
        assertEquals(0, usuario.getId(), "Id deve ser nulo pre Acesso ao banco");
        assertEquals("Carlos", usuario.getNome(), "Nome deve ser Carlos");
        assertEquals("12345678900", usuario.getCpf(), "CPF deve ser 12345678900");
    }

    @Test
    void devePermitirAtualizarEmail() {
        usuario.setEmail("novo@email.com");
        assertEquals("novo@email.com", usuario.getEmail(), "Email deve ser atualizado");
    }

    @Test
    void deveConterDataNascimentoValida() {
        assertNotNull(usuario.getDataNascimento(), "Data de nascimento não deve ser nula");
        assertEquals(1990, usuario.getDataNascimento().getYear(), "Ano de nascimento deve ser 1990");
    }

    @Test
    void tipoUsuarioNaoDeveSerNulo() {
        assertNotNull(usuario.getTipoUsuario(), "Tipo do usuário não deve ser nulo");
        assertTrue(!usuario.getTipoUsuario().isEmpty(), "Tipo do usuário não deve estar vazio");
    }
}
