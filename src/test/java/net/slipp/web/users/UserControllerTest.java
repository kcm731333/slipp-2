package net.slipp.web.users;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import net.slipp.dao.users.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(userController).build();
	}
	
	
	@Test
	public void createWhenValid() throws Exception {
		
		this.mockMvc.perform(
				post("/users")
				.param("userId", "aa111")
				.param("password", "pa111")
				.param("email", "a@a.com")
				.param("name", "아아")
				)
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void createWhendInvalid() throws Exception {
		
		this.mockMvc.perform(
				post("/users")
				.param("userId", "aa111")
				.param("password", "1")
				.param("email", "a@a.com")
				.param("name", "아아")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("users/form"));
	}

}
