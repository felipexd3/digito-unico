package com.fb.onedigit.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fb.onedigit.builders.OneDigitBuilders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Digito único - Integração")
public class OneDigitIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve encontrar digito único com sucesso")
    public void shouldCalculateOneDigit() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/one-digit")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(OneDigitBuilders
                .builder(String.valueOf(new Random().nextInt(1000000000)),
                    new Random().nextInt(100000)))))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test()
    @DisplayName("Deve falhar devido máximo permitido")
    public void shouldFailCalculateOneDigitWhenExpIsHigher() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/one-digit")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(OneDigitBuilders
                .builder("abc", 100001))))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test()
    @DisplayName("Deve falhar devido número nulo")
    public void shouldFailCalculateOneDigitWhenNumberIsNull() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/one-digit")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(OneDigitBuilders
                .builder(null, 100000))))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar 200 na busca de uma lista")
    public void shouldListAllOneDigitofUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .get("/one-digit/user/8411a3eb-1803-4ce2-8c2c-579c364ccfab"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
