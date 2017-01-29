package com.sar.sandpit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by genio on 22/01/17.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest
//@AutoConfigureMockMvc
//@AutoConfigureWebMvc
@WebAppConfiguration
//@Transactional
public class ItemControllerIntegrationTest {

    //@Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    ItemServiceable itemService;

    @Before
    public void setUp(){
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void AddItem_success() throws Exception {
        Item item = new Item(1L, "one task");
        when(itemService.add(item)).thenReturn(item);

        mockMvc.perform(
                get("/todo/addItemTest/")
                        .param("id","1")
                        .param("details","taska")
        ).
                andExpect(status().isOk())
                .andReturn();


//        verify(itemService).add(item);
//        verifyNoMoreInteractions(itemService);
    }

    @Test
    public void addItem2_Success() throws Exception {
        mockMvc.perform(
                post("/todo/addItem/")
                        .param("id", "1")
                        .param("details", "tasks")
        ).andExpect(status().isOk())
                .andReturn();

    }
}
