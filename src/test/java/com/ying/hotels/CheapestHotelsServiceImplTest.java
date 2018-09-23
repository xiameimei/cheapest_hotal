package com.ying.hotels;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Chloe on 2018/9/23.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class CheapestHotelsServiceImplTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getCheapestHotelsTest_statusOk() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/hotels/search_cheapest?location={location}&check_in={checkin}",
                "yvr","2018-10-10")).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String rsp= result.getResponse().getContentAsString();
        Assert.assertEquals(getResponseCode(rsp),200);
    }

    @Test
    public void getCheapestHotelsTest_status400() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/hotels/search_cheapest?location={location}&check_in={checkin}",
                "yvr","2018-10")).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String rsp= result.getResponse().getContentAsString();
        Assert.assertEquals(getResponseCode(rsp),400);
    }

    private int getResponseCode(String result) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        return rootNode.path("code").asInt();

    }
}
