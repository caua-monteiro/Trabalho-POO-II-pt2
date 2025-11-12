package com.appacademia.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AcademiaTest {

    private Academia academia;

    @BeforeEach
    void setUp() {
        academia = new Academia();
        academia.setNome("First Gym");
        academia.setCnpj("12345678000199");
        academia.setLocalizacao("Rua A, 100");
        academia.setTelefone("(11)99999-9999");
    }

    @Test
    void deveCriarAcademiaComCamposCorretos() {
        assertEquals(0, academia.getId(), "Id deve ser nulo ate acesso ao banco");
        assertEquals("First Gym", academia.getNome(), "Nome deve ser FitGym");
        assertEquals("12345678000199", academia.getCnpj(), "CNPJ deve coincidir");
    }

    @Test
    void telefoneDeveEstarFormatado() {
        assertNotNull(academia.getTelefone(), "Telefone não deve ser nulo");
        assertTrue(academia.getTelefone().contains(")"), "Telefone deve ter formato esperado");
    }

    @Test
    void localizacaoNaoDeveSerVazia() {
        assertFalse(academia.getLocalizacao().isEmpty(), "Localização não deve ser vazia");
    }

    @Test
    void podeAtualizarNome() {
        academia.setNome("SuperFit");
        assertEquals("SuperFit", academia.getNome(), "Nome deve ser atualizado para SuperFit");
    }
}
