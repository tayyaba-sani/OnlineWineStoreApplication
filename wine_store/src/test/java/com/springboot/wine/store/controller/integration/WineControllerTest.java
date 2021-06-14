//package com.springboot.wine.store.controller.integration;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.springboot.wine.store.WineStoreApplicationTests;
//import com.springboot.wine.store.common.Utils.Constants;
//import com.springboot.wine.store.dtos.ResponseHandler;
//import com.springboot.wine.store.dtos.WineDTO;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class WineControllerTest extends WineStoreApplicationTests {
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
//    public void addWineTest() throws Exception {
//        String url = "/wines/addWine";
//        WineDTO wineDTO = new WineDTO();
//        wineDTO.setCountry("Canada");
//        wineDTO.setDescription("This is Canadian Wine");
//        wineDTO.setName("Canadian Wine");
//        wineDTO.setRating(8);
//        wineDTO.setRegion("ABC");
//        wineDTO.setRetailPrice(7f);
//        wineDTO.setVarietal("ABC");
//        wineDTO.setWineVersion(9);
//        wineDTO.setYear(2008);
//        String jsonRequest = objMapper.writeValueAsString(wineDTO);
//
//        MvcResult mvcResult = mockMvc.perform(post(url).
//                content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//
//        String resultContent = mvcResult.getResponse().getContentAsString();
//        ResponseHandler<WineDTO> responseHandler = objMapper.readValue(resultContent, ResponseHandler.class);
//        assert responseHandler.getData().getId() > 0;
//
//    }
//
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
//}
