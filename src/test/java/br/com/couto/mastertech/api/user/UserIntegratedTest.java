package br.com.couto.mastertech.api.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.couto.mastertech.model.UsuarioModel;
import br.com.couto.mastertech.service.UsuarioService;
import br.com.couto.mastertech.service.UsuarioServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegratedTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Mock
	private static UsuarioService userService;

	@BeforeAll
	static void setup() {
		userService = new UsuarioServiceImpl();
	}

	@Test
	void testFindAllUsers() throws JsonProcessingException, Exception {
		List<UsuarioModel> usersMock = new ArrayList<>();
		UsuarioModel user = new UsuarioModel();
		user.setCpf("31640949404");
		user.setFullName("Manuel Joaquim");
		user.setEmail("manuel.joaquim@gmail.com");
		user.setRegistrationDate(new Date());
		
		UsuarioModel user2 = new UsuarioModel();
		user2.setId(1L);
		user2.setCpf("31625162866");
		user2.setFullName("Jorge Martins");
		user2.setEmail("jirge.martins@gmail.com");
		user2.setRegistrationDate(new Date());
		
		UsuarioModel user3 = new UsuarioModel();
		user3.setCpf("36910240847");
		user3.setFullName("Maria Helena ");
		user3.setEmail("mariahelena@gmail.com");
		user3.setRegistrationDate(new Date());
		
		usersMock.add(user);
		usersMock.add(user2);
		usersMock.add(user3);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/find")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(usersMock)))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.when(userService.findAll()).thenReturn(usersMock);
		
		List<UsuarioModel> users = userService.findAll();
		assertNotNull(users.size());		
	}
	
	@Test
	void testFindUserById() throws JsonProcessingException, Exception {
		UsuarioModel user2 = new UsuarioModel();
		user2.setId(1L);
		user2.setCpf("36910240847");
		user2.setFullName("Maria Helena ");
		user2.setEmail("mariahelena@gmail.com");
		user2.setRegistrationDate(new Date());

		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/find/{idUser}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user2)))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.when(userService.findById(1L)).thenReturn(user2);
		
		UsuarioModel user = userService.findById(1L);
		
		assertEquals(user.getId(), user2.getId());		
	}
	
	@Test
	void testSaveSuccess() throws JsonProcessingException, Exception {
		UsuarioModel user2 = new UsuarioModel();
		user2.setId(1L);		
		user2.setCpf("36910240847");
		user2.setFullName("Maria Helena ");
		user2.setEmail("mariahelena@gmail.com");
		user2.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user2)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.when(userService.save(user2)).thenReturn(user2);
		
		UsuarioModel user = userService.save(user2);
		assertEquals(user.getCpf(), user2.getCpf());		
	}
	
	@Test
	void testSaveWithValidationCPFNullError() throws JsonProcessingException, Exception {
		UsuarioModel user = new UsuarioModel();
		user.setCpf("31640949404");
		user.setFullName("Manuel Joaquim");
		user.setEmail("manuel.joaquim@gmail.com");
		user.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationCPFEmptyError() throws JsonProcessingException, Exception {
		UsuarioModel user = new UsuarioModel();
		user.setCpf("");
		user.setFullName("Mario Neves");
		user.setEmail("mario@gmail.com");
		user.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationNomeNullError() throws JsonProcessingException, Exception {
		UsuarioModel user = new UsuarioModel();
		user.setCpf("36910240847");		
		user.setEmail("jose@gmail.com");
		user.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationNomeEmptyError() throws JsonProcessingException, Exception {
		UsuarioModel user = new UsuarioModel();
		user.setCpf("36910240847");
		user.setFullName("");
		user.setEmail("paul.miller@gmail.com");
		user.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationEmailNullError() throws JsonProcessingException, Exception {
		UsuarioModel user2 = new UsuarioModel();
		user2.setCpf("36910240847");
		user2.setFullName("Patrick Mackel");		
		user2.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user2)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationEmailEmptyError() throws JsonProcessingException, Exception {
		UsuarioModel user = new UsuarioModel();
		user.setCpf("36910240847");
		user.setFullName("Marcos Pereira");
		user.setEmail("");
		user.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void testSaveWithValidationRegistrationDateNullError() throws JsonProcessingException, Exception {
		UsuarioModel user = new UsuarioModel();
		user.setCpf("36910240847");
		user.setFullName("Bruno Gobil");
		user.setEmail("bruno.gobil@gmail.com");
				
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void testSaveEditSuccess() throws JsonProcessingException, Exception {
		UsuarioModel user = new UsuarioModel();
		user.setCpf("36910240847");
		user.setFullName("Milton Cruz");
		user.setEmail("milton.cruzs@gmail.com");
		user.setRegistrationDate(new Date());
				
		mockMvc.perform(
				MockMvcRequestBuilders.put("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.when(userService.save(user)).thenReturn(user);
		
		UsuarioModel user2 = userService.save(user);
		assertEquals(user2.getCpf(), user.getCpf());		
	}

}