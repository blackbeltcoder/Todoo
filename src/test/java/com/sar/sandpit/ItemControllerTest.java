package com.sar.sandpit;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.ModelResultMatchers.*;

/**
 * Created by genio on 20/11/16.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc   // full mvc mock
@WebMvcTest//(ItemController.class)   // Just the web context
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemServiceable itemService ;//= new ItemService();
//    @MockBean
//    ItemStorable itemStorable;
    @Captor
    ArgumentCaptor<Item> itemCapture;


    @Before
    public void init(){
        mockMvc =MockMvcBuilders.standaloneSetup(new ItemController(itemService)).build();


    }



    @Test
    public void addItem_whenItemDoesnotExit_success() throws Exception {



        final String task = "tasks";
        final long id = 1L;
        Item item = new Item(id, task);


        //when(itemService.add(item)).thenReturn( new Item());
        when(itemService.add(item)).thenReturn(item);


        mockMvc.perform(
                post("/todo/addItem/")
                        .param("id","1")
                        .param("details","taska")
        )
                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("1")))
//                .andExpect(content().string(containsString("taska")))
                //.andExpect(model().attributeExists("item"))
                //.andExpect(model().attribute("item",is(equalTo(item))))

                //.andExpect(view().name("todo/addItemResult"))
                .andReturn();
        verify(itemService,times(1)).add(itemCapture.capture());


    }
    @Test
    public void addItem_whenItemDoesnotExit_success2() throws Exception {
        mockMvc.perform(
                post("/todo/addItem/")
                        .param("id","99")
                        .param("details","taskz")
        )
                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("99")))
//                .andExpect(content().string(containsString("taskz")))
                .andExpect(view().name("todo/addItemResult"))
                .andReturn();

    }

    @Test
    public void addItem_WhenItemDoesnotExist_success() throws Exception {


        final String task = "a taskq";
        final long id = 11L;
        Item item = new Item(id, task);


        //when(itemService.add(item)).thenReturn( new Item());
        when(itemService.add(item)).thenReturn(item);

        mockMvc.perform(
                post("/todo/addItem/")
                        .param("id",Long.toString(id))
                        .param("details",task)
        )
                .andExpect(status().isOk());

        verify(itemService,times(1)).add(itemCapture.capture());
        assertThat("should be equal", item, is(equalTo(itemCapture.getValue())));

    }

    @Test
    public void displayItem_StoreHasOneItem_Success() throws Exception {

        Long id =1l;
        String detail="task";



        when(itemService.getItem(id)).thenReturn(new Item(id,detail));
        mockMvc.perform(get("/todo/item/"+id))
                .andExpect(status().isOk())
                .andExpect(view().name("todo/item"))
                .andExpect(model().attribute("item", is(notNullValue())))

        //.andExpect();
        ;

        verify(itemService).getItem(id);
       // throw new NotImplementedException();

        

    }
    @Test
    public void displayItem_StoreHasOneItem_Success2() throws Exception {

        Long id = 2l;
        String detail = "task2";
        Item item = new Item(id, detail);


        when(itemService.getItem(id)).thenReturn(item);
        mockMvc.perform(get("/todo/item/"+id))
                .andExpect(status().isOk())
                .andExpect(model().attribute("item", is(equalTo(item))))

        //.andExpect();
        ;

        verify(itemService).getItem(id);
        // throw new NotImplementedException();
    }


}
