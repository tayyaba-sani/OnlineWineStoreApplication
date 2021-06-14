package com.springboot.wine.store.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.dtos.CartItemDTO;
import com.springboot.wine.store.entities.CartItem;
import com.springboot.wine.store.services.ShoppingCartItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CartItemController.class)
class CartItemControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHappyPathAddToCart() throws Exception {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setWineId(2);
        cartItemDTO.setCustomerId(2);
        cartItemDTO.setQuantity(3);

        CartItem cartItem = new CartItem();

        when(shoppingCartItemService.addWineItem(cartItemDTO.getWineId(), cartItemDTO.getQuantity(), cartItemDTO.getCustomerId()))
                .thenReturn(cartItem);

        String jsonRequest = mapper.writeValueAsString(cartItemDTO);
        String url = "/cartItems";
        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        CartItemDTO responseEntity = mapper.readValue(resultContent, CartItemDTO.class);
        verify(shoppingCartItemService).addWineItem(cartItemDTO.getWineId(), cartItemDTO.getQuantity(), cartItemDTO.getCustomerId());
        assertThat(responseEntity).isNotNull();

    }

    @Test
    void addToCartThrowBusinessException() throws Exception {
        CartItemDTO cartItemDTO = new CartItemDTO();
        String jsonRequest = mapper.writeValueAsString(cartItemDTO);
        String url = "/cartItems";
        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();

        assertThat(mvcResult.getResolvedException().getMessage()).isEqualTo(Constants.CART_ITEM_DETAIL_NOT_COMPLETED);


    }

    @Test
    void addToCartThrowBadRequest() throws Exception {
        CartItemDTO cartItemDTO = new CartItemDTO();
        when(shoppingCartItemService.addWineItem(cartItemDTO.getWineId(), cartItemDTO.getQuantity(), cartItemDTO.getCustomerId()))
                .thenReturn(null);
        String jsonRequest = mapper.writeValueAsString(cartItemDTO);
        String url = "/cartItems";
        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());


    }
}