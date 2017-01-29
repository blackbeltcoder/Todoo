package com.sar.sandpit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by genio on 20/11/16.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc   // full mvc mock
@WebMvcTest//(ItemController.class)   // Just the web context
@WebAppConfiguration
public class ItemControllerTest {

    public static final String TODO_DELETE_ITEM_URL = "/todo/deleteItem/";
    // @Autowired
    MockMvc mockMvc;

    //@Autowired
    @MockBean
    ItemServiceable itemServiceable;//= new ItemService();
//    @MockBean
//    ItemStorable itemStorable;
    @Captor
    ArgumentCaptor<Item> itemCapture;
    @Captor
    ArgumentCaptor<Long> itemIdCapture;
   // @Autowired
    private WebApplicationContext wac;


    @Before
    public void init(){
        mockMvc =MockMvcBuilders.standaloneSetup(new ItemController(itemServiceable)).build();
       // mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build();


    }



    @Test
    public void addItem_whenItemDoesnotExit_success() throws Exception {



        final String task = "tasks";
        final long id = 1L;
        Item item = new Item(id, task);


        //when(itemServiceable.add(item)).thenReturn( new Item());
        when(itemServiceable.add(item)).thenReturn(item);


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
        verify(itemServiceable, times(1)).add(itemCapture.capture());


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


        //when(itemServiceable.add(item)).thenReturn( new Item());
        when(itemServiceable.add(item)).thenReturn(item);

        mockMvc.perform(
                post("/todo/addItem/")
                        .param("id",Long.toString(id))
                        .param("details",task)
        )
                .andExpect(status().isOk());

        verify(itemServiceable, times(1)).add(itemCapture.capture());
        assertThat("should be equal", item, is(equalTo(itemCapture.getValue())));

    }

    //////////////////////////////
    // todo display single item
    /////////////////////////////
    @Test
    public void displayItem_StoreHasOneItem_Success() throws Exception {

        Long id =1l;
        String detail="task";



        when(itemServiceable.getItem(id)).thenReturn(new Item(id, detail));
        mockMvc.perform(get("/todo/item/"+id))
                .andExpect(status().isOk())
                .andExpect(view().name("todo/item"))
                .andExpect(model().attribute("item", is(notNullValue())))

        //.andExpect();
        ;

        verify(itemServiceable).getItem(id);
       // throw new NotImplementedException();

        

    }
    @Test
    public void displayItem_StoreHasOneItem_Success2() throws Exception {

        Long id = 2l;
        String detail = "task2";
        Item item = new Item(id, detail);


        when(itemServiceable.getItem(id)).thenReturn(item);
        mockMvc.perform(get("/todo/item/"+id))
                .andExpect(status().isOk())
                .andExpect(model().attribute("item", is(equalTo(item))))

        //.andExpect();
        ;

        verify(itemServiceable).getItem(id);
        // throw new NotImplementedException();
    }

    //////////////////////////////
    // todo display multiple items
    /////////////////////////////


    @Test
    public void displayMultipleItems_Success() throws Exception {


        when(itemServiceable.getItems()).thenReturn(Arrays.asList(new Item(), new Item()));
        mockMvc.perform(get("/todo/items/"))
                .andExpect(status().isOk())
                .andReturn();
        verify(itemServiceable,times(1)).getItems();

    }
    @Test
    public void displayMultipleItems2_Success() throws Exception {
        //when(itemServiceable.getItems()).thenReturn(Arrays.asList(new Item(), new Item()));
        mockMvc.perform(get("/todo/items/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("items"))
                .andExpect(view().name(is("todo/items")))
                .andReturn();
        //verify(itemServiceable,times(1)).getItems();

    }

   // @Test
    public void deleteItemByItem_GivenItemToDelete_Success() throws Exception {
        Item item =new Item();
        when(itemServiceable.delete(item)).thenReturn(true);
        mockMvc.perform(post(TODO_DELETE_ITEM_URL))
                .andExpect(status().isOk())
                .andReturn();
        verify(itemServiceable,times(1)).delete(itemCapture.capture());
        assertThat(" should match Item ",item,is(equalTo(itemCapture.getValue())));

    }
    @Test
    public void deleteItemById_GivenItemToDelete_Success() throws Exception {
        Long id =1L;
        when(itemServiceable.delete(id)).thenReturn(true);
        mockMvc.perform(post(TODO_DELETE_ITEM_URL +id))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
        ;

        verify(itemServiceable,times(1)).delete(itemIdCapture.capture());
        assertThat(" should match Item ",id,is(equalTo(itemIdCapture.getValue())));

    }

    @Test
    public void deleteItemById_GivenItemToDelete_ReturnsSuccessPage() throws Exception {
        Long id =1L;
        mockMvc.perform(post(TODO_DELETE_ITEM_URL +id))
                .andExpect(status().isOk())
                .andExpect(view().name(is("todo/items")))
                .andExpect(forwardedUrl("todo/items"))
                .andDo(print())
                .andReturn()
        ;

    }
}
