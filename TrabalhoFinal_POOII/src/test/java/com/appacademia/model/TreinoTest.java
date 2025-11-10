package com.appacademia.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TreinoTest {

    private Treino treino;

    @BeforeEach
    void setUp() {
        treino = new Treino();
        treino.setIdUsuario(1);
        treino.setTipoTreino("Força");
        treino.setPdfTreino("http://exemplo/treino.pdf");
        treino.setDataCriacao(LocalDateTime.of(2025, 1, 1, 10, 0));
        treino.setObservacoes("Foco em membros superiores");
    }

    @Test
    void deveCriarTreinoComCamposPreenchidos() {
        assertNull(treino.getId(), "Id deve ser nulo ate acesso do banco");
        assertEquals("Força", treino.getTipoTreino(), "Tipo de treino deve ser Força");
        assertEquals(1, treino.getIdUsuario(), "ID do usuário deve ser 1 para o teste");
    }

    @Test
    void pdfDoTreinoDeveSerUrlNaoNula() {
        assertNotNull(treino.getPdfTreino(), "PDF do treino não deve ser nulo");
        assertTrue(treino.getPdfTreino().startsWith("http"), "PDF deve ser uma URL");
    }

    @Test
    void dataCriacaoDeveSerAnteriorAoAgora() {
        assertNotNull(treino.getDataCriacao(), "Data de criação não deve ser nula");
        assertTrue(treino.getDataCriacao().getYear() <= LocalDateTime.now().getYear(),
                "Data de criação não deve ser no futuro (verifique lógica de criação)");
    }

    @Test
    void observacoesPodeSerAlterada() {
        treino.setObservacoes("Alterado: ênfase em pernas");
        assertEquals("Alterado: ênfase em pernas", treino.getObservacoes(), "Observações devem ser atualizadas");
    }
}
