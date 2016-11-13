package com.sar.sandpit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by N460906 on 13/10/2016.
 */

@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Before
    public void setUp() throws Exception {


        MockitoAnnotations.initMocks(this);
    }

    @MockBean
    ItemStorable itemStorable;

    @Captor
    ArgumentCaptor<Item> itemCapture;

    @Spy
    //@SpyBean
    @InjectMocks
    private ItemServiceable itemService =new ItemService( itemStorable);

    @Test
    public  void addTaskItem_success() throws Exception {
        Item item =new Item();
        itemService.add(item);


        verify(itemStorable).save(itemCapture.capture());
        assertThat("Item should be added", itemCapture.getValue(), is(equalTo(item)));


    }

    @Test
    public void deleteTaskItem_success() throws Exception {
        Item item= new Item();
        itemService.delete(item);


        verify(itemStorable).delete(itemCapture.capture());
        assertThat("item should be deleted ",itemCapture.getValue(), is(equalTo(item)));



    }


    @Test
    public void updateTask_Success() throws Exception {
        Item item = new Item();

        itemService.updateItem(item);

        verify(itemStorable).save(itemCapture.capture());
        assertThat("item should be deleted ",itemCapture.getValue(), is(equalTo(item)));

    }
}
