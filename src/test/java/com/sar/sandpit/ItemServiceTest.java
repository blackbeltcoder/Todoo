package com.sar.sandpit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by N460906 on 13/10/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
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

    @Captor
    ArgumentCaptor<Long> idCapture;

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
    public void deleteTaskItem_returnFalse() throws Exception {
        //Arrange
        final long id = 1L;
        when(itemStorable.exists(id)).thenReturn(false);

        boolean res = itemService.delete(new Item(1L));
        assertThat("should be true", res, is(false));
        verify(itemStorable).exists(id);
    }
    @Test
    public void deleteTaskItem_returnTrue() throws Exception {

        //Arrange
        final long id = 1L;
        when(itemStorable.exists(id)).thenReturn(true);
        //Act
        boolean res = itemService.delete(new Item(id));
        //Assert
        assertThat("should be true", res, is(true));
        verify(itemStorable).exists(id);
    }

    @Test
    public void updateTask_Success() throws Exception {
        Item item = new Item();

        itemService.updateItem(item);

        verify(itemStorable).save(itemCapture.capture());
        assertThat("item should be deleted ",itemCapture.getValue(), is(equalTo(item)));
    }

    @Test
    public void getItem_success() throws Exception {

        Long id = 1L;
        itemService.getItem(id);

        verify(itemStorable).findOne(idCapture.capture());
        assertThat("id should match",idCapture.getValue(),is(equalTo(id)));
    }

    @Test
    public void getItem_success2() throws Exception {

        //Arrange
        when(itemStorable.findOne(1L)).thenReturn(new Item(1L));
        Item res =itemService.getItem(1L);
        assertThat("item not null", res, is(notNullValue()));

    }
}
