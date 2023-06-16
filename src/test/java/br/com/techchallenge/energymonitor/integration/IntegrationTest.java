package br.com.techchallenge.energymonitor.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    
    private static final String jsonPessoa_OK = """
            {
              "nome": "Fulano de Tal",
              "dataNascimento": "27-12-1987",
              "genero": "MASCULINO",
              "parentesco": "PAI"
            }
        """;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldValidatePessoaDto() throws Exception {

        var request = post("/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonPessoa_OK);

        var expected = status().isOk();
        
        mockMvc.perform(request)
            .andDo(print())
            .andExpect(expected);
    }

}
