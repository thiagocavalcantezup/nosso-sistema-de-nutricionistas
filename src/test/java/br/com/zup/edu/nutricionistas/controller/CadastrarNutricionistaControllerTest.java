package br.com.zup.edu.nutricionistas.controller;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.edu.nutricionistas.model.Nutricionista;
import br.com.zup.edu.nutricionistas.repository.NutricionistaRepository;
import br.com.zup.edu.nutricionistas.util.MensagemDeErro;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("test")
public class CadastrarNutricionistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NutricionistaRepository nutriRepository;

    private String NUTRI_URI = "/nutricionistas";

    @BeforeEach
    void setUp() {
        nutriRepository.deleteAll();
    }

    @Test
    void deveCadastrarUmNutricionistaComDadosValidos() throws Exception {
        // cenário (given)
        //
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        NutricionistaRequest nutriRequest = new NutricionistaRequest(
            "Thiago", "thiago@example.com", "12345", "123.456.789-09",
            LocalDate.now().minusYears(35)
        );

        String payload = objectMapper.writeValueAsString(nutriRequest);

        MockHttpServletRequestBuilder request = post(NUTRI_URI).contentType(APPLICATION_JSON)
                                                               .content(payload)
                                                               .header("Accpet-Language", "pt-br");

        // ação (when) e corretude (then)
        //
        mockMvc.perform(request)
               .andExpect(status().isCreated())
               .andExpect(redirectedUrlPattern(baseUrl + NUTRI_URI + "/*"));

        List<Nutricionista> nutricionistas = nutriRepository.findAll();

        assertEquals(1, nutricionistas.size());
    }

    @Test
    void naoDeveCadastrarUmNutricionistaComDadosInvalidos() throws Exception {
        // cenário (given)
        //
        NutricionistaRequest nutriRequest = new NutricionistaRequest(null, null, null, null, null);

        String payload = objectMapper.writeValueAsString(nutriRequest);

        MockHttpServletRequestBuilder request = post(NUTRI_URI).contentType(APPLICATION_JSON)
                                                               .content(payload)
                                                               .header("Accept-Language", "pt-br");

        // ação (when) e corretude (then)
        //
        String response = mockMvc.perform(request)
                                 .andExpect(status().isBadRequest())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString(UTF_8);

        MensagemDeErro mensagemDeErro = objectMapper.readValue(response, MensagemDeErro.class);
        List<String> mensagens = mensagemDeErro.getMensagens();

        assertEquals(5, mensagens.size());
        assertThat(
            mensagens,
            containsInAnyOrder(
                "O campo nome não deve estar em branco", "O campo email não deve estar em branco",
                "O campo CRN não deve estar em branco", "O campo cpf não deve estar em branco",
                "O campo dataNascimento não deve ser nulo"
            )
        );
    }

    @Test
    void naoDeveCadastrarUmNutricionistaComEmailInvalido() throws Exception {
        // cenário (given)
        //
        NutricionistaRequest nutriRequest = new NutricionistaRequest(
            "Thiago", "thiago@example,com", "12345", "123.456.789-09",
            LocalDate.now().minusYears(35)
        );

        String payload = objectMapper.writeValueAsString(nutriRequest);

        MockHttpServletRequestBuilder request = post(NUTRI_URI).contentType(APPLICATION_JSON)
                                                               .content(payload)
                                                               .header("Accept-Language", "pt-br");

        // ação (when) e corretude (then)
        //
        String response = mockMvc.perform(request)
                                 .andExpect(status().isBadRequest())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString(UTF_8);

        MensagemDeErro mensagemDeErro = objectMapper.readValue(response, MensagemDeErro.class);
        List<String> mensagens = mensagemDeErro.getMensagens();

        assertEquals(1, mensagens.size());
        assertThat(
            mensagens,
            containsInAnyOrder("O campo email deve ser um endereço de e-mail bem formado")
        );
    }

    @Test
    void naoDeveCadastrarUmNutricionistaComCpfInvalido() throws Exception {
        // cenário (given)
        //
        NutricionistaRequest nutriRequest = new NutricionistaRequest(
            "Thiago", "thiago@example.com", "12345", "123.456.789-00",
            LocalDate.now().minusYears(35)
        );

        String payload = objectMapper.writeValueAsString(nutriRequest);

        MockHttpServletRequestBuilder request = post(NUTRI_URI).contentType(APPLICATION_JSON)
                                                               .content(payload)
                                                               .header("Accept-Language", "pt-br");

        // ação (when) e corretude (then)
        //
        String response = mockMvc.perform(request)
                                 .andExpect(status().isBadRequest())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString(UTF_8);

        MensagemDeErro mensagemDeErro = objectMapper.readValue(response, MensagemDeErro.class);
        List<String> mensagens = mensagemDeErro.getMensagens();

        assertEquals(1, mensagens.size());
        assertThat(
            mensagens,
            containsInAnyOrder(
                "O campo cpf número do registro de contribuinte individual brasileiro (CPF) inválido"
            )
        );
    }

    @Test
    void naoDeveCadastrarUmNutricionistaComDataDeNascimentoQueNaoSejaNoPassado() throws Exception {
        // cenário (given)
        //
        NutricionistaRequest nutriRequest = new NutricionistaRequest(
            "Thiago", "thiago@example.com", "12345", "123.456.789-09", LocalDate.now()
        );

        String payload = objectMapper.writeValueAsString(nutriRequest);

        MockHttpServletRequestBuilder request = post(NUTRI_URI).contentType(APPLICATION_JSON)
                                                               .content(payload)
                                                               .header("Accept-Language", "pt-br");

        // ação (when) e corretude (then)
        //
        String response = mockMvc.perform(request)
                                 .andExpect(status().isBadRequest())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString(UTF_8);

        MensagemDeErro mensagemDeErro = objectMapper.readValue(response, MensagemDeErro.class);
        List<String> mensagens = mensagemDeErro.getMensagens();

        assertEquals(1, mensagens.size());
        assertThat(
            mensagens, containsInAnyOrder("O campo dataNascimento deve ser uma data passada")
        );
    }

}
