package br.com.couto.mastertech.controller;

import br.com.couto.mastertech.model.PontoEletronicoModel;
import br.com.couto.mastertech.service.pontoeletronico.PontoEletronicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PontoEletronicoControllerTest {

    @MockBean
    private PontoEletronicoService pontoEletronicoService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllPunchesByUser() {
        List<PontoEletronicoModel> pontos = new ArrayList<>();
        pontos.add(new PontoEletronicoModel(null, "23:59:59", new ArrayList<>()));

        when(pontoEletronicoService.findAll((long) 1)).thenReturn(pontos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("idUsuario", "1");
        HttpEntity<String> requestEntity = new HttpEntity<>("{}", headers);

        ParameterizedTypeReference<List<PontoEletronicoModel>> responseType =
                new ParameterizedTypeReference<List<PontoEletronicoModel>>() {
                };
        ResponseEntity<List<PontoEletronicoModel>> usuarioResponse =
                restTemplate.exchange("/pontoeletronico",
                        HttpMethod.GET,
                        requestEntity,
                        responseType);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(usuarioResponse.getBody(), pontos);
    }

    @Test
    public void shouldToPunchTheClock() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idUsuario", "2");
        HttpEntity<String> requestEntity = new HttpEntity<>("{}", headers);

        ResponseEntity<String> usuarioResponse =
                restTemplate.exchange("/pontoeletronico",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void shouldNotToPunchTheClock() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("idUsuario", "3");
        HttpEntity<String> requestEntity = new HttpEntity<>("{}", headers);

        doThrow(Exception.class).when(pontoEletronicoService).register((long) 3);

        ResponseEntity<String> usuarioResponse =
                restTemplate.exchange("/pontoeletronico",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
