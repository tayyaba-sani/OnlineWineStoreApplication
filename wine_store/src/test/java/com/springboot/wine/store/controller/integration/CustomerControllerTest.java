//package com.springboot.wine.store.controller.integration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.springboot.wine.store.WineStoreApplicationTests;
//import com.springboot.wine.store.dtos.CustomerDTO;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class CustomerControllerTest extends WineStoreApplicationTests {
//
//    ObjectMapper objMapper = new ObjectMapper();
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void createCustomerTest() throws Exception {
//        String url = "/customers/createCustomer";
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setEmail("mkhan@gmail.com");
//        customerDTO.setFirstName("Maheen");
//        customerDTO.setLastName("Khan");
//        customerDTO.setPhone("0900584");
//        String jsonRequest = objMapper.writeValueAsString(customerDTO);
//
//        MvcResult mvcResult = mockMvc.perform(post(url).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//
//        String resultContent = mvcResult.getResponse().getContentAsString();
////        ResponseHandler responseHandler = objMapper.readValue(resultContent, ResponseHandler.class);
////        assert ((CustomerDTO) responseHandler.getData()).getId() > 0;
////        Assert.assertEquals();
//    }
//}
