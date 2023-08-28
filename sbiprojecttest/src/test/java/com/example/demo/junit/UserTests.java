package com.example.demo.junit;
import java.sql.Date;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(UserController.class)
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

  

    @MockBean
    private UserRepository userRepository;

    java.sql.Date d= new java.sql.Date(20010405);
    java.sql.Date da= new java.sql.Date(20010405);
    
    @Test
    public void testGetAllUsers() throws Exception {
        List<User> c = new ArrayList<>();
        // AddUser instances to the list
       
       User c1 = new User("12345","Kishor","Sharma","11223344","Dhananjay","abc,hyderabad","abc,hyderabad",d,"9867543210","sharma@gmail.com","234565786543","1E23455678","Engineer",5000d);
        //User b1 = newUser("2",1,2,"s");
       User c2 = new User("12346","Kishori","Sharma","112233445","Dhananjay","abc,hyderabad","abc,hyderabad",d,"9887543210","isharma@gmail.com","294565786543","1E2455678","Engineer",5000d);
        //User b2 = newUser("4",9,6,"6");
        c.add(c1);
        c.add(c2);
        when(userRepository.findAll()).thenReturn(c);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(c.size()));
    }
    

    
    @Test
    public void testCreateUser() throws Exception {
        //UserUser = newUser(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
     User newUser = new User("12345","Kishor","Sharma","11223344","Dhananjay","abc,hyderabad","abc,hyderabad",d,"9867543210","sharma@gmail.com","234565786543","1E23455678","Engineer",5000d);
    	User createUser = new User("12345","Kishor","Sharma","11223344","Dhananjay","abc,hyderabad","abc,hyderabad",d,"9867543210","sharma@gmail.com","234565786543","1E23455678","Engineer",5000d);
       
    
       
        when(userRepository.save(any(User.class))).thenReturn(createUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sendUser")
                        .contentType(MediaType.APPLICATION_JSON)
                         .content(asJsonString(newUser)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.account.accountType").value(createdAccount.getAccountType()));
}
    
    
    public static String asJsonString(final User newUser) {
    	
    	    try {
    	        final ObjectMapper mapper = new ObjectMapper();
    	        final String jsonContent = mapper.writeValueAsString(newUser);
    	        return jsonContent;
    	    } catch (Exception e) {
    	        throw new RuntimeException(e);
    	    }
    	}  
    	
	


	@Test
    public void testUpdateUserById() throws Exception {
        //UserUser = newUser(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
      User existingUser = new User("12345","Kishor","Sharma","11223344","Dhananjay","abc,hyderabad","abc,hyderabad",d,"9867543210","sharma@gmail.com","234565786543","1E23455678","Engineer",5000d);
      User updatedUser = new User("12345","Kishor","Sharma","11223344","Dhananjay","ab,banglore","abc,hyderabad",d,"9867543210","sharma@gmail.com","234565786543","1E23455678","Engineer",5000d);

        when(userRepository.findById(anyString())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateUser/{id}","12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedUser)))
                .andExpect(status().isOk());
             //   .andExpect(jsonPath("$.balance").value(updatedAccount.getBalance()));
    }
    
  
    @Test
    public void testDeleteAccount() throws Exception {
       //UserUser = newUser(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
      User existingUser = new User("12345","Kishor","Sharma","11223344","Dhananjay","abc,hyderabad","abc,hyderabad",d,"9867543210","sharma@gmail.com","234565786543","1E23455678","Engineer",5000d);

        when(userRepository.findById("12345")).thenReturn(Optional.of(existingUser));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteUser/{id}", "12345"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userRepository, times(1)).delete(existingUser);
    }
    
    @Test
    public void find_byIdNotFound_404() throws Exception {
        mockMvc.perform(get("/api/v1/Users/12387")).andExpect(status().isNotFound());
    }


}
