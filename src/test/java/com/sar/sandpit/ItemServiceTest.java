package com.sar.sandpit;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Mock
    ItemStorable itemStorable;

    @Captor
    ArgumentCaptor<Item> itemCapture;

    @Spy
    @InjectMocks
    private ItemServiceable itemService =new ItemService( itemStorable);

    @Test
    public  void addTaskItem_success() throws Exception {
        Item item =new Item();
        itemService.add(item);


        verify(itemStorable).addItem(itemCapture.capture());
        assertThat("Item should be added", itemCapture.getValue(), is(equalTo(item)));


    }

    @Test
    public void name() throws Exception {

    }
}
