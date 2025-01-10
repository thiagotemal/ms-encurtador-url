package br.com.temal.urlshorter;

import br.com.temal.urlshorter.controller.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class ShorterUrlControllerTests extends AbstractBaseIntegrationTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	private ObjectMapper objectMapper = new ObjectMapper();
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void postShorterUrl() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/urls")
					.contentType("application/json")
					.content("{ \"originalUrl\": \"https://www.example.com\" }"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		var response = objectMapper.readValue(result.getResponse().getContentAsString(), Response.class);
		assertEquals("https://www.example.com", response.originalUrl());

	}
	@Test
	void postShorterUrl_withoutOriginalUrl() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/urls")
					.contentType("application/json")
					.content("{}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andReturn();

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}

	@Test
	void postShorterUrl_withInvalidOriginalUrl() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/urls")
					.contentType("application/json")
					.content("{ \"originalUrl\": \"invalid\" }"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andReturn();

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}


}
