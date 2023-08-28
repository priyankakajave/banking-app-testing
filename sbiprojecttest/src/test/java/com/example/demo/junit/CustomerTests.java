package com.example.demo.junit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.CustomerController;
import com.example.demo.model.Customer;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.CustomerRepository;
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
@WebMvcTest(CustomerController.class)
public class CustomerTests {

    @Autowired
    private MockMvc mockMvc;

  //  @MockBean
   // private BeneficiaryRepository bR;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> c = new ArrayList<>();
        // AddCustomer instances to the list
        Customer c1 = new Customer(1L, "1234567","abHcd@123","762435");
        //Customer b1 = new Customer("2",1,2,"s");
        Customer c2 = new Customer(2L, "2234567","aSbcd@123","762435");
        //Customer b2 = new Customer("4",9,6,"6");
        c.add(c1);
        c.add(c2);
        when(customerRepository.findAll()).thenReturn(c);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(c.size()));
    }.
    
    
    @Test
    public void testCreateCustomer() throws Exception {
        //Customer customer = new Customer(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
      Customer newCustomer = new Customer(1L, "1234567","abWcd@123","762435");
       Customer createCustomer = new Customer(2L, "1434567","abWcd@123","762435");
       
    
       
        when(customerRepository.save(any(Customer.class))).thenReturn(createCustomer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sendCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newCustomer)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.account.accountType").value(createdAccount.getAccountType()));
}
    
    
    public static String asJsonString(final Customer newCustomer) {
    	
    	    try {
    	        final ObjectMapper mapper = new ObjectMapper();
    	        final String jsonContent = mapper.writeValueAsString(newCustomer);
    	        return jsonContent;
    	    } catch (Exception e) {
    	        throw new RuntimeException(e);
    	    }
    	}  
    	
	


	@Test
    public void testUpdateCustomerById() throws Exception {
        //Customer customer = new Customer(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
       Customer existingCustomer = new Customer(1L, "1234567","abcA@123","762435");
       Customer updatedCustomer = new Customer(1L, "1234567","123@Abcd","762435");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateCustomer/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedCustomer)))
                .andExpect(status().isOk());
             //   .andExpect(jsonPath("$.balance").value(updatedAccount.getBalance()));
    }
    
  
    @Test
    public void testDeleteCustomer() throws Exception {
       // Customer customer = new Customer(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
       Customer existingCustomer = new Customer(3L, "1234567","abcAd@123","762435");

        when(customerRepository.findById(3L)).thenReturn(Optional.of(existingCustomer));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteCustomer/{id}", 3L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(customerRepository, times(1)).delete(existingCustomer);
    }
    
    @Test
    public void find_byIdNotFound_404() throws Exception {
        mockMvc.perform(get("/api/v1/customers/8")).andExpect(status().isNotFound());
    }


}
