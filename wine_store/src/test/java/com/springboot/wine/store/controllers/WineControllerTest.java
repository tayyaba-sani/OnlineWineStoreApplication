package com.springboot.wine.store.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.services.WineService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WineController.class)
class WineControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private WineService wineService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHappyPathForAddWine() throws Exception {
        Wine wine = new Wine();
        wine.setName("Red wine");
        when(wineService.addWine(any())).thenReturn(wine);

        WineDTO wineDTO = new WineDTO();
        wineDTO.setName("Red wine");

        String jsonRequest = mapper.writeValueAsString(wineDTO);
        String url = "/wines";
        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        WineDTO responseEntity = mapper.readValue(resultContent, WineDTO.class);
        verify(wineService).addWine(any());
        assertThat(responseEntity).isNotNull();

    }

    @Test
    void addWineThrowBusinessException() throws Exception {

        WineDTO wineDTO = new WineDTO();
        String jsonRequest = mapper.writeValueAsString(wineDTO);
        String url = "/wines";
        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();

        assertThat(mvcResult.getResolvedException().getMessage()).isEqualTo(Constants.WINE_DETAILS_NOT_COMPLETED);

    }

    @Test
    void testHappyPathForGetWineById() throws Exception {
        Wine wine = new Wine();
        wine.setName("Red wine");
        long id = 2;
        when(wineService.findWineById(id)).thenReturn(wine);

        String url = "/wines/" + id;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        WineDTO responseEntity = mapper.readValue(resultContent, WineDTO.class);
        verify(wineService).findWineById(id);
        assertThat(responseEntity).isNotNull();
    }

    @Test
    void getWineByIdNotFound() throws Exception {
        Wine wine = new Wine();
        wine.setName("Red wine");
        long id = 2;
        when(wineService.findWineById(id)).thenReturn(null);

        String url = "/wines/" + id;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void testHappyPathForGetWineByYear() throws Exception {
        List<Wine> wineList = new ArrayList<>();
        Wine wine = new Wine();
        wine.setName("Red wine");
        wineList.add(wine);
        int year = 2020;
        when(wineService.getWineFindByYear(year)).thenReturn(wineList);

        String url = "/wines/findByYear/" + year;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        List<WineDTO> responseEntity = mapper.readValue(resultContent, new TypeReference<List<WineDTO>>() {
        });
        verify(wineService).getWineFindByYear(year);
        assertThat(responseEntity.size()).isGreaterThan(0);
    }


    @Test
    void getWineByYearNotFount() throws Exception {
        int year = 2020;
        when(wineService.getWineFindByYear(year)).thenReturn(null);

        String url = "/wines/findByYear/" + year;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void testHappyPathForGetWineByCountry() throws Exception {
        List<Wine> wineList = new ArrayList<>();
        Wine wine = new Wine();
        wine.setName("Red wine");
        wineList.add(wine);
        String country = "Germany";
        when(wineService.getWineFindByCountry(country)).thenReturn(wineList);

        String url = "/wines/findByCountry/" + country;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        List<WineDTO> responseEntity = mapper.readValue(resultContent, new TypeReference<List<WineDTO>>() {
        });
        verify(wineService).getWineFindByCountry(country);
        assertThat(responseEntity.size()).isGreaterThan(0);

    }

    @Test
    void getWineByCountryNotFount() throws Exception {
        String country = "Germany";
        when(wineService.getWineFindByCountry(country)).thenReturn(null);

        String url = "/wines/findByCountry/" + country;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void getWineByVarietal() throws Exception {
        List<Wine> wineList = new ArrayList<>();
        Wine wine = new Wine();
        wine.setName("Red wine");
        wineList.add(wine);
        String varietal = "abc";
        when(wineService.getWineFindByVarietal(varietal)).thenReturn(wineList);

        String url = "/wines/findByVarietal/" + varietal;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        List<WineDTO> responseEntity = mapper.readValue(resultContent, new TypeReference<List<WineDTO>>() {
        });
        verify(wineService).getWineFindByVarietal(varietal);
        assertThat(responseEntity.size()).isGreaterThan(0);
    }

    @Test
    void getWineByVarietalNotFount() throws Exception {
        String varietal = "abc";
        when(wineService.getWineFindByVarietal(varietal)).thenReturn(null);

        String url = "/wines/findByVarietal/" + varietal;
        MvcResult mvcResult = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}