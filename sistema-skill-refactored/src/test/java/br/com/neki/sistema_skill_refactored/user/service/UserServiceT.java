package br.com.neki.sistema_skill_refactored.user.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "testUser", roles = { "SIMPLE" })
	public void shouldReturnAllUsersWhenGetRequestIsMade() throws Exception {
		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").isNotEmpty());
	}

	@Test
	@WithMockUser(username = "testUser", roles = { "SIMPLE" })
	public void shouldReturnNotFoundForNonExistentUserId() throws Exception {
		UUID userId = UUID.randomUUID();
		mockMvc.perform(get("/users/{id}", userId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

	
}
