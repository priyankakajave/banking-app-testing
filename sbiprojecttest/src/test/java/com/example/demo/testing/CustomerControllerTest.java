package com.example.demo.testing;


import com.example.demo.controller.CustomerController;
import com.example.demo.model.Customer;
import com.example.demo.repository.BeneficiaryRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
//
//
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
//
//
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//
import org.springframework.test.web.servlet.MockMvc;
//
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository mockRepository;
    
    @MockBean
    private BeneficiaryRepository beneRepo;
    
    @MockBean
    private TransactionRepository tR;
    
    @MockBean
    private UserRepository uR;
    

    @Before(value = "")
    public void init() {
        Customer customer = new Customer(1L, "a", "b", "c");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(customer));
  }

    
    
    @Test
    public void findByCustomerID_OK() throws Exception {

        mockMvc.perform(get("/api/v1/customers/1"))
              //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.password", is("a")))
                .andExpect(jsonPath("$.CustomerID", is("b")))
                .andExpect(jsonPath("$.TransactionPassword", is("c")));

        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    public void getAllCustomers_OK() throws Exception {

        List<Customer> customers = Arrays.asList(
                new Customer(1L, "a", "b", "c"),
                new Customer(2L, "e", "f","g"));

        when(mockRepository.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].password", is("a")))
                .andExpect(jsonPath("$[0].CustomerID", is("b")))
                .andExpect(jsonPath("$[0].TransactionPassword", is("c")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].password", is("e")))
                .andExpect(jsonPath("$[1].CustomerID", is("f")))
                .andExpect(jsonPath("$[1].TransactionPassword", is("g")));

        verify(mockRepository, times(1)).findAll();
    }

    @Test
    public void find_customerIdNotFound_404() throws Exception {
        mockMvc.perform(get("/api/v1/customers/5")).andExpect(status().isNotFound());
    }

    @Test
    public void createCustomer_OK() throws Exception {

        Customer newCustomer = new Customer(1L, "j", "k", "l");
        when(mockRepository.save(any(Customer.class))).thenReturn(newCustomer);

        mockMvc.perform(post("/api/v1/sendCustomer")
                .content(om.writeValueAsString(newCustomer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
              //
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.password", is("j")))
                .andExpect(jsonPath("$.CustomerID", is("k")))
                .andExpect(jsonPath("$.TransactionPassword", is("l")));

        verify(mockRepository, times(1)).save(any(Customer.class));
    }

//    @Test
//    public void testcreate() throws Exception{
//    	Customer c=new Customer();
//    	c.setCustomerID("aa");
//    	c.setID((long) 1);
//    	c.setPassword("pass");
//    	c.setTransactionPassword("tp");
//    	
//    	Mockito.when(CustomerController.createCustomer(ArgumentMatchers.any())).thenReturn(c);
//    	String json=mapper.writeValueAsString(c);
//    	mvc.perform(post("/api/v1/sendCustomer").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//    			.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//    		
//    }
    
    @Test
    public void updateCustomer_OK() throws Exception {

        Customer updateCustomer = new Customer(1L, "x", "y", "z");
        when(mockRepository.save(any(Customer.class))).thenReturn(updateCustomer);

        mockMvc.perform(put("/api/v1/updateCustomer/1")
                .content(om.writeValueAsString(updateCustomer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.password", is("x")))
                .andExpect(jsonPath("$.CustomerID", is("y")))
                .andExpect(jsonPath("$.TransactionPassword", is("z")));


    }


    @Test
    public void deleteCustomer_OK() throws Exception {

        doNothing().when(mockRepository).deleteById(1L);

        mockMvc.perform(delete("/api/v1/deleteCustomer/1"))
             
                .andExpect(status().isOk()).andReturn();

        verify(mockRepository, times(1)).deleteById(1L);
    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
