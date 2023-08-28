package com.example.demo.junit;
import java.sql.Date;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.TransactionController;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
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
@WebMvcTest(TransactionController.class)
public class TransactionTests {

    @Autowired
    private MockMvc mockMvc;

 

    @MockBean
    private TransactionRepository transactionRepository;

    java.sql.Date d= new java.sql.Date(20230405);
    java.sql.Date da= new java.sql.Date(20220405);
    
    @Test
    public void testGetAllTransactions() throws Exception {
        List<Transaction> c = new ArrayList<>();
        // AddTransaction instances to the list
       
       Transaction c1 = new Transaction(12345678L,13245678L,1000D,"y",d);
        //Transaction b1 = newTransaction("2",1,2,"s");
       Transaction c2 = new Transaction(12325678L,13245678L,1000D,"y",d);
        //Transaction b2 = newTransaction("4",9,6,"6");
        c.add(c1);
        c.add(c2);
        when(transactionRepository.findAll()).thenReturn(c);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(c.size()));
    }
    

    
    @Test
    public void testCreateTransaction() throws Exception {
        //TransactionTransaction = newTransaction(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
     Transaction newTransaction = new Transaction(12345678L,13245678L,1000D,"y",da);
      Transaction createTransaction = new Transaction(12345678L,13245678L,1000D,"y",da);
       
    
       
        when(transactionRepository.save(any(Transaction.class))).thenReturn(createTransaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sendTransaction")
                        .contentType(MediaType.APPLICATION_JSON)
                         .content(asJsonString(newTransaction)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.account.accountType").value(createdAccount.getAccountType()));
}
    
    
    public static String asJsonString(final Transaction newTransaction) {
    	
    	    try {
    	        final ObjectMapper mapper = new ObjectMapper();
    	        final String jsonContent = mapper.writeValueAsString(newTransaction);
    	        return jsonContent;
    	    } catch (Exception e) {
    	        throw new RuntimeException(e);
    	    }
    	}  
    	
	


	@Test
    public void testUpdateTransactionById() throws Exception {
        //TransactionTransaction = newTransaction(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
      Transaction existingTransaction = new Transaction(12345678L,13245678L,1000D,"y",da);
      Transaction updatedTransaction = new Transaction(12345678L,13245678L,1000D,"y",d);

        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(existingTransaction));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(updatedTransaction);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateTransaction/{id}",12345678L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedTransaction)))
                .andExpect(status().isOk());
             //   .andExpect(jsonPath("$.balance").value(updatedAccount.getBalance()));
    }
    
  
    @Test
    public void testDeleteTransaction() throws Exception {
       //TransactionTransaction = newTransaction(1L, "John Doe", "john@example.com", "1234567890", "123 Main St", "Cityville", "Regionland", "12345");
      Transaction existingTransaction = new Transaction(12345678L,13245678L,1000D,"y",d);

        when(transactionRepository.findById(12345678L)).thenReturn(Optional.of(existingTransaction));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteTransaction/{id}", 12345678L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(transactionRepository, times(1)).delete(existingTransaction);
    }
    
    @Test
    public void find_byIdNotFound_404() throws Exception {
        mockMvc.perform(get("/api/v1/Transactions/87965438")).andExpect(status().isNotFound());
    }


}
