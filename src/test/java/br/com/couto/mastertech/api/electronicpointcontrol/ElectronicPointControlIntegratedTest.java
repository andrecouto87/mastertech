package br.com.couto.mastertech.api.electronicpointcontrol;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.couto.mastertech.api.electronicpointcontrol.model.ElectronicPointControl;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.EletronicPointControlVO;
import br.com.couto.mastertech.api.electronicpointcontrol.service.ElectronicPointControlService;
import br.com.couto.mastertech.api.electronicpointcontrol.service.impl.ElectronicPointControlServiceImpl;
import br.com.couto.mastertech.api.user.model.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ElectronicPointControlIntegratedTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private static ElectronicPointControlService electronicPointControlService;

	@BeforeAll
	static void setup() {
		electronicPointControlService = new ElectronicPointControlServiceImpl();
	}

	@Test
	void testFindByUser() throws JsonProcessingException, Exception {
		List<ElectronicPointControlDTO> dtos = new ArrayList<>();
		List<ElectronicPointControl> ElectronicPiontControlMock = new ArrayList<>();
		User user = new User();
		user.setId(1L);
		user.setCpf("36912040847");
		user.setFullName("Jorge Martins");
		user.setEmail("jorgemartins@gmail.com");
		user.setRegistrationDate(new Date());

		ElectronicPointControlDTO dto = new ElectronicPointControlDTO();
		dto.setUsuario(user);
		dto.setEletricPointControl(new ArrayList<EletronicPointControlVO>());
		
		dto.getEletricPointControl().add(new EletronicPointControlVO("17-06-2020", new ArrayList<>()));
		
		dtos.add(dto);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/electronicpointcontrol/find/{idUser}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.when(electronicPointControlService.findByUser(Mockito.anyLong())).thenReturn(dtos);

		List<ElectronicPointControlDTO> electronicPointControl = electronicPointControlService.findByUser(1L);
		assertNotNull(electronicPointControl.get(0));
		assertNotNull(electronicPointControl.get(0).getEletricPointControl());

		Mockito.verify(electronicPointControlService, VerificationModeFactory.atLeastOnce()).findByUser(Mockito.anyLong());
	}

	@Test
	void testFindByUserReturningNull() throws JsonProcessingException, Exception {
		List<ElectronicPointControl> ElectronicPiontControlMock = new ArrayList<>();
		User user = new User();
		user.setId(1000000000000000000L);
		user.setCpf("36912040847");
		user.setFullName("Jorge Martins");
		user.setEmail("jorgemartins@gmail.com");
		user.setRegistrationDate(new Date());

		ElectronicPointControlDTO dto = new ElectronicPointControlDTO();
		dto.setUsuario(user);
		dto.setEletricPointControl(new ArrayList<EletronicPointControlVO>());
		
		dto.getEletricPointControl().add(new EletronicPointControlVO("17-06-2020", new ArrayList<>()));
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/electronicpointcontrol/find/{idUser}", 10L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		List<ElectronicPointControlDTO> electronicPointControl = electronicPointControlService.findByUser(10L);
		assertNull(electronicPointControl.size() == 0 ? null : electronicPointControl.get(0));
		assertNull(electronicPointControl.size() == 0 ? null : electronicPointControl.get(0).getEletricPointControl());		
	}

	@Test
	void testFindByUserBadRequest() throws JsonProcessingException, Exception {
		User user = new User();
		user.setId(1L);
		user.setCpf("36912040847");
		user.setFullName("Jorge Martins");
		user.setEmail("jorgemartins@gmail.com");
		user.setRegistrationDate(new Date());

		ElectronicPointControlDTO dto = new ElectronicPointControlDTO();
		dto.setUsuario(user);
		dto.setEletricPointControl(new ArrayList<EletronicPointControlVO>());
		
		dto.getEletricPointControl().add(new EletronicPointControlVO("17-06-2020", new ArrayList<>()));
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/electronicpointcontrol/find/{idUser}", "10L")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
}
