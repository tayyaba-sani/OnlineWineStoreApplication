package com.springboot.wine.store.controller.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wine.store.controllers.WineController;
import com.springboot.wine.store.dtos.WineDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = WineController.class)
@AutoConfigureMockMvc
public class WineControllerTest {

    ObjectMapper objMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addWineTest() throws Exception {
        String url = "/wines";
        WineDTO wineDTO = new WineDTO();
        wineDTO.setCountry("Turkey");
        wineDTO.setDescription("This is Turkey Wine");
        wineDTO.setName("Turkey Wine");
        wineDTO.setRating(9);
        wineDTO.setRegion("ABC");
        wineDTO.setRetailPrice(10f);
        wineDTO.setVarietal("ABC");
        wineDTO.setWineVersion(2);
        wineDTO.setYear(2001);
        String jsonRequest = objMapper.writeValueAsString(wineDTO);

        MvcResult mvcResult = mockMvc.perform(post(url).
                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String resultContent = mvcResult.getResponse().getContentAsString();
        WineDTO responseHandler = objMapper.readValue(resultContent, WineDTO.class);
        assert responseHandler.getId() > 0;

    }

//    @Test
//    public void getWineByIdTest() throws Exception {
//        String url = "/wines/findById/25";
//        MvcResult mvcResult = mockMvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//        String resultContent = mvcResult.getResponse().getContentAsString();
//        ResponseHandler<WineDTO> responseHandler = objMapper.readValue(resultContent, ResponseHandler.class);
//        if (responseHandler.getData() != null) {
//            assert responseHandler.getData().getId() > 0;
//        } else {
//            assert ((String) responseHandler.getMessage().getDescription()).equals(Constants.RECORD_NOT_FOUND);
//        }
//    }
//
//    @Test
//    public void getWineByYearTest() throws Exception {
//        String url = "/wines/findByYear/2006";
//        MvcResult mvcResult = mockMvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//        String resultContent = mvcResult.getResponse().getContentAsString();
//        ResponseHandler<WineDTO> responseHandler = objMapper.readValue(resultContent, ResponseHandler.class);
//        if (responseHandler.getData() != null) {
//            assert responseHandler.getData().getId() > 0;
//        } else {
//            assert ((String) responseHandler.getMessage().getDescription()).equals(Constants.RECORD_NOT_FOUND);
//        }
//    }
//
//    @Test
//    public void getWineByCountryTest() throws Exception {
//        String url = "/wines/findByCountry/Bangladesh";
//        MvcResult mvcResult = mockMvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//        String resultContent = mvcResult.getResponse().getContentAsString();
//        ResponseHandler<WineDTO> responseHandler = objMapper.readValue(resultContent, ResponseHandler.class);
//        if (responseHandler.getData() != null) {
//            assert responseHandler.getData().getId() > 0;
//        } else {
//            assert ((String) responseHandler.getMessage().getDescription()).equals(Constants.RECORD_NOT_FOUND);
//        }
//    }
//
//    @Test
//    public void getWineByVarietalTest() throws Exception {
//        String url = "/wines/findByVarietal/ABC";
//        MvcResult mvcResult = mockMvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//        String resultContent = mvcResult.getResponse().getContentAsString();
//        ResponseHandler<WineDTO> responseHandler = objMapper.readValue(resultContent, ResponseHandler.class);
//        if (responseHandler.getData() != null) {
//            assert responseHandler.getData().getId() > 0;
//        } else {
//            assert ((String) responseHandler.getMessage().getDescription()).equals(Constants.RECORD_NOT_FOUND);
//        }
//    }
}
