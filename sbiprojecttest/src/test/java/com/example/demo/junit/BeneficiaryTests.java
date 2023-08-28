package com.example.demo.junit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.BeneficiaryController;
import com.example.demo.model.Beneficiary;
import com.example.demo.model.Customer;
import com.example.demo.repository.BeneficiaryRepository;
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
@WebMvcTest(BeneficiaryController.class)
public class BeneficiaryTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeneficiaryRepository beneficiaryRepository;

  

    @Test
    public void testGetAllBeneficiary() throws Exception {
        List<Beneficiary> b = new ArrayList<>();
        // AddBeneficiary instances to the list
        //Customer c1 = new Customer(1L, "a","b","c");
        Beneficiary b1 = new Beneficiary("12345678",34256,2,"dhwani");
        //Customer c2 = new Customer(2L, "d","e","f");
        Beneficiary b2 = new Beneficiary("12345698",34456,1,"dhwanii");
        b.add(b1);
        b.add(b2);
        when(beneficiaryRepository.findAll()).thenReturn(b);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beneficiaries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(b.size()));
    }
    
    
    @Test
    public void testCreateBeneficiary() throws Exception {
        //Customer customer = new Customer(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
       Beneficiary newBeneficiary = new Beneficiary("12345678",34256,2,"dhwani");
       Beneficiary createBeneficiary = new Beneficiary("12345678",34256,2,"dhwani");
       
    
       
        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(createBeneficiary);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sendBeneficiary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newBeneficiary)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.account.accountType").value(createdAccount.getAccountType()));
}
    
    
    public static String asJsonString(final Beneficiary newBeneficiary) {
    	
    	    try {
    	        final ObjectMapper mapper = new ObjectMapper();
    	        final String jsonContent = mapper.writeValueAsString(newBeneficiary);
    	        return jsonContent;
    	    } catch (Exception e) {
    	        throw new RuntimeException(e);
    	    }
    	}  
    	
	


	@Test
    public void testUpdateBeneficiaryById() throws Exception {
        //Customer customer = new Customer(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
       Beneficiary existingBeneficiary = new Beneficiary("12345678",34256,2,"dhwani");
       Beneficiary updatedBeneficiary = new Beneficiary("12345678",32256,1,"dhanya");

        when(beneficiaryRepository.findById(anyString())).thenReturn(Optional.of(existingBeneficiary));
        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(updatedBeneficiary);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateBeneficiary/{id}","12345678")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedBeneficiary)))
                .andExpect(status().isOk());
             //   .andExpect(jsonPath("$.balance").value(updatedAccount.getBalance()));
    }
    
  
    @Test
    public void testDeleteBeneficiary() throws Exception {
       // Customer customer = new Customer(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
       Beneficiary existingBeneficiary = new Beneficiary("12345678",34256,2,"dhwani");

        when(beneficiaryRepository.findById("12345678")).thenReturn(Optional.of(existingBeneficiary));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteBeneficiary/{id}", "12345678"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(beneficiaryRepository, times(1)).delete(existingBeneficiary);
    }
    
    @Test
    public void find_byIdNotFound_404() throws Exception {
        mockMvc.perform(get("/api/v1/beneficiarys/98765432q")).andExpect(status().isNotFound());
    }


}
