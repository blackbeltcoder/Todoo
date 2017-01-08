package com.sar.sandpit;

import org.hamcrest.CoreMatchers;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.ModelResultMatchers.*;

/**
 * Created by genio on 20/11/16.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc    full mvc mock
@WebMvcTest   // Just the web context
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;
    @Captor
    ArgumentCaptor<Item> itemCapture;


    @Test
    public void displayItem_StoreHasOneItem_Success() throws Exception {

        Long id =1l;
        String detail="task";



        when(itemService.getItem(id)).thenReturn(new Item(id,detail));
        mockMvc.perform(get("/todo/item"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("item", is(notNullValue())))
        //.andExpect();
        ;

        verify(itemService).getItem(id);
       // throw new NotImplementedException();

        

    }


}
