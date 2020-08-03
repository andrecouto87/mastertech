package br.com.couto.mastertech.controller;

import br.com.couto.mastertech.model.UsuarioModel;
import br.com.couto.mastertech.service.usuario.UsuarioService;
import br.com.couto.mastertech.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllUsers() {
        List<UsuarioModel> usuarios = new ArrayList<>();
        usuarios.add(MockUtil.getMockedUsuarioModel());

        when(usuarioService.findAll()).thenReturn(usuarios);

        ParameterizedTypeReference<List<UsuarioModel>> responseType =
                new ParameterizedTypeReference<List<UsuarioModel>>() {
                };
        ResponseEntity<List<UsuarioModel>> usuarioResponse = restTemplate.exchange("/usuario",
                HttpMethod.GET,
                null,
                responseType);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(usuarioResponse.getBody(), usuarios);
    }

    @Test
    public void shouldReturnOnlyOneUser() throws Exception {
        UsuarioModel usuario = MockUtil.getMockedUsuarioModel();

        when(usuarioService.findById((long) 1)).thenReturn(usuario);

        ResponseEntity<UsuarioModel> usuarioResponse = restTemplate.getForEntity("/usuario/1",
                UsuarioModel.class);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(usuarioResponse.getBody(), usuario);
    }

    @Test
    public void shouldNotReturnUser() throws Exception {
        when(usuarioService.findById((long) 2)).thenThrow(new Exception("Usuário não encontrado."));

        ResponseEntity<UsuarioModel> usuarioResponse = restTemplate.getForEntity("/usuario/2",
                UsuarioModel.class);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldSaveUser() {
        UsuarioModel usuario = MockUtil.getMockedUsuarioModel();

        when(usuarioService.save(usuario)).thenReturn(usuario);

        ResponseEntity<UsuarioModel> usuarioResponse = restTemplate.postForEntity("/usuario",
                usuario,
                UsuarioModel.class);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldEditUser() throws Exception {
        UsuarioModel usuario = MockUtil.getMockedUsuarioModel();

        HttpEntity<UsuarioModel> requestEntity = new HttpEntity<>(usuario);

        when(usuarioService.edit((long) 3, usuario)).thenReturn(usuario);

        ResponseEntity<UsuarioModel> usuarioResponse = restTemplate.exchange("/usuario/3",
                HttpMethod.PUT,
                requestEntity,
                UsuarioModel.class);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotEditUser() throws Exception {
        UsuarioModel usuario = MockUtil.getMockedUsuarioModel();

        HttpEntity<UsuarioModel> requestEntity = new HttpEntity<>(usuario);

        when(usuarioService.edit((long) 4, usuario)).thenThrow(new Exception("Usuário não encontrado."));

        ResponseEntity<UsuarioModel> usuarioResponse = restTemplate.exchange("/usuario/4",
                HttpMethod.PUT,
                requestEntity,
                UsuarioModel.class);

        assertEquals(usuarioResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
