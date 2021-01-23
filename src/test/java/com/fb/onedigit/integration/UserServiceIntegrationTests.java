package com.fb.onedigit.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fb.onedigit.builders.UserBuilders;
import com.fb.onedigit.models.builders.UserDTOBuilder;
import com.fb.onedigit.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Usuários - Integração")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj/OQrpTeyC3xKisZ2u3OuHOgUveiNe7lvEDic856wo" +
        "bP1vsNuCKStyNunwZX+HVbTlpEiOx0dMwY1L0vrDoSUtFz12MK/WF5qijG9RoDS7nNi+4aXwh5ihWHMeNHEM8vnKRHEib+omhsE7DryISH8IG" +
        "ZJYc5l15ppNoHnOw4Wqbb1t1mjZ33DTdG1z0pEf8nX+eD1wjOs8TzRySawAHgRKx/zHSoR6DD8b0LsaoU2SFgHGN2DwgWgSE+/sDarBvMe9/k" +
        "xv5Ev28GBRIk8OmyE6wIVzshu3wRRXfJl7LH5009TGkrDlAEUjrDkxiR6jjyn5c3zvoZaqJqNG20DdvzsQIDAQAB";

    @Test
    @DisplayName("Deve cadastrar usuário")
    public void shouldInsertUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/users")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(UserBuilders.builder())))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Não deve cadastrar usuário")
    public void shouldntInsertUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/users")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(UserBuilders.builderWithError())))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deve buscar usuário")
    public void shouldFindUser() throws Exception {
        var user = userRepository.save(UserDTOBuilder.toEntity(UserBuilders.builder()));
        this.mockMvc.perform(MockMvcRequestBuilders
            .get("/users/"+user.getUid().toString())
            .header("public-key", publicKey))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deve encontrar usuário")
    public void shouldntFindUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .get("/users/8411a3eb-1803-4ce2-8c2c-579c364ccfab")
            .header("public-key", publicKey))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Deve atualizar usuário")
    public void shouldUpdateUser() throws Exception {
        var user = userRepository.save(UserDTOBuilder.toEntity(UserBuilders.builder()));
        this.mockMvc.perform(MockMvcRequestBuilders
            .put("/users/"+user.getUid().toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(UserDTOBuilder.fromEntity(user))))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve remover usuário")
    public void shouldRemoveUser() throws Exception {
        var user = userRepository.save(UserDTOBuilder.toEntity(UserBuilders.builder()));
        this.mockMvc.perform(MockMvcRequestBuilders
            .delete("/users/"+user.getUid().toString()))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
