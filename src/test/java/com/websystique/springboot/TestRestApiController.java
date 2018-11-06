package com.websystique.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.websystique.springboot.model.User;
import com.websystique.springboot.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TestRestApiController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void pruebaGetEntoncesUsuarioExiste() {
		User user = new User(199, "Domingo", 23, 2222);
		try {
			Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(user);

			mockMvc.perform(MockMvcRequestBuilders.get("/api/user/2"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("age").value(23));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void pruebaGetEntoncesUsuarioNoExiste(){
	
			Mockito.when(userService.findById(Mockito.anyLong())).thenThrow(RuntimeException.class);

			try {
				mockMvc.perform(MockMvcRequestBuilders.get("/api/user/2"))
						.andExpect(MockMvcResultMatchers.status().isServiceUnavailable());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
