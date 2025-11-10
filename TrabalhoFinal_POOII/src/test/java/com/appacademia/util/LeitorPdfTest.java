package com.appacademia.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeitorPdfTest {

    @Test
    void LerApiCloudName() {
        String cloudName = LeitorPDF.getProperty("cloudinary.cloud-name");
        assertNotNull(cloudName, "cloudinary.cloud-name deve estar presente no config.properties");
        assertFalse(cloudName.isEmpty(), "cloudinary.cloud-name não deve estar vazio");
    }

    @Test
    void LerApiKey() {
        String apiKey = LeitorPDF.getProperty("cloudinary.api-key");
        assertNotNull(apiKey, "cloudinary.api-key deve estar presente no config.properties");
        assertFalse(apiKey.isEmpty(), "cloudinary.api-key não deve estar vazio");
    }

    @Test
    void LerApiSecret() {
        String secret = LeitorPDF.getProperty("cloudinary.api-secret");
        assertNotNull(secret, "cloudinary.api-secret deve estar presente no config.properties");
        assertFalse(secret.isEmpty(), "cloudinary.api-secret não deve estar vazio");
    }

    @Test
    void propriedadeInexistenteRetornaNull() {
        String valor = LeitorPDF.getProperty("propriedade.que.nao.existe");
        assertNull(valor, "Propriedade inexistente deve retornar null");
    }
}
