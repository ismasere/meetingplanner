


package fr.kata.meetingplanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 @author SERE
 @since 14 août 2024
**/

@WebMvcTest(RegisterTest.class)
public class RegisterTest {


	    @Autowired
	    private MockMvc mockMvc;
	    

	    
//	    @Test
//	    public void testUserRegister() throws Exception {
//	        mockMvc.perform(get("/api/auth/register/"))
//	                .andExpect(status().isOk())
//	                .andExpect((ResultMatcher) content().contentType("application/json"))
//	                .andExpect(jsonPath("$.firstname").value("Soumaila"))
//	                .andExpect(jsonPath("$.lastname").value("SERE"))
//	                .andExpect(jsonPath("$.email").value("sere@gmail.com"))
//	                .andExpect(jsonPath("$.password").value("12345678"));
//	    }
	
}

