package com.springboot.wine.store.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.dtos.CustomerDTO;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.entities.Customer;
import com.springboot.wine.store.entities.WineItem;
import com.springboot.wine.store.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {


    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHappyPathForCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("tayyaba.sani@yahoo.com");
        when(customerService.registerCustomer(any())).thenReturn(customer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmail("tayyaba.sani@yahoo.com");

        String jsonRequest = mapper.writeValueAsString(customerDTO);
        String url = "/customers";
        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        CustomerDTO responseEntity = mapper.readValue(resultContent, CustomerDTO.class);
        verify(customerService).registerCustomer(any());
        assertThat(responseEntity).isNotNull();
    }

    @Test
    void createCustomerThrowBusinessException() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        String jsonRequest = mapper.writeValueAsString(customerDTO);
        String url = "/customers";
        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();

        assertThat(mvcResult.getResolvedException().getMessage()).isEqualTo(Constants.CUSTOMER_DETAIL_NOT_COMPLETED);

    }

    @Test
    void testHappyPathForGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("tayyaba.sani@yahoo.com");
        long id = 2;
        when(customerService.getCustomerFindById(id)).thenReturn(customer);

        String url = "/customers/" + id;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        CustomerDTO responseEntity = mapper.readValue(resultContent, CustomerDTO.class);
        verify(customerService).getCustomerFindById(id);
        assertThat(responseEntity).isNotNull();
    }

    @Test
    void getCustomerByIdNotFound() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("tayyaba.sani@yahoo.com");
        long id = 2;
        when(customerService.getCustomerFindById(id)).thenReturn(null);

        String url = "/customers/" + id;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void testHappyPathForGetCustomerByEmail() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("tayyaba.sani@yahoo.com");
        String email = "tayyaba.sani@yahoo.com";
        when(customerService.getCustomerFindByEmail(email)).thenReturn(customer);

        String url = "/customers/findByEmail/" + email;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        CustomerDTO responseEntity = mapper.readValue(resultContent, CustomerDTO.class);
        verify(customerService).getCustomerFindByEmail(email);
        assertThat(responseEntity).isNotNull();

    }

    @Test
    void getCustomerByEmailNotFount() throws Exception {
        String email = "tayyaba.sani@yahoo.com";
        when(customerService.getCustomerFindByEmail(email)).thenReturn(null);

        String url = "/customers/findByEmail/" + email;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }


    @Test
    void removeCustomerById() throws Exception {

        long id = 2;
        doNothing().when(customerService).removeCustomer(id);

        String url = "/customers/" + id;
        MvcResult mvcResult = mockMvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        assertThat(HttpStatus.OK.value()).isEqualTo(200);
        verify(customerService).removeCustomer(id);
    }

    @Test
    void testHappyPathForGetCartItems() throws Exception {
        List<CartItem> cartItemList = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItem.setCustomer(new Customer());
        cartItem.setWineItem(new WineItem());
        cartItemList.add(cartItem);

        long id = 2;
        when(customerService.getCustomerCartItemList(id)).thenReturn(cartItemList);

        String url = "/customers/" + id + "/cart-items";
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        List<CartItemDTO> responseEntity = mapper.readValue(resultContent, new TypeReference<List<CartItemDTO>>() {
        });
        verify(customerService).getCustomerCartItemList(id);
        assertThat(responseEntity.size()).isGreaterThan(0);

    }

    @Test
    void getCartItemsNotFound() throws Exception {
        long id = 2;
        when(customerService.getCustomerCartItemList(id)).thenReturn(null);

        String url = "/customers/" + id + "/cart-items";
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void testHappyPathForSubmitOrder() throws Exception{
        long id = 2;
        when(customerService.processOrder(id)).thenReturn(Constants.SUCCESS_ORDER_SUBMITTED);

        String url = "/customers/" + id + "/submit-order";
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        verify(customerService).processOrder(id);
        assertThat(resultContent).isEqualTo(Constants.SUCCESS_ORDER_SUBMITTED);

    }
    @Test
    void submitOrderThrowBusinessException() throws Exception{
        long id = 2;
        when(customerService.processOrder(id)).thenReturn(null);

        String url = "/customers/" + id + "/submit-order";
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();
        verify(customerService).processOrder(id);
        assertThat(mvcResult.getResolvedException().getMessage()).isEqualTo(Constants.CUSTOMER_DOES_NOT_EXIST);

    }
}