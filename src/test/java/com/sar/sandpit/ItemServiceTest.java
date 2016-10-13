package com.sar.sandpit;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
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

    @Spy
    @InjectMocks
    private ItemServiceable itemService =new ItemService( itemStorable);

    @Test
    public  void addTaskItem_success() throws Exception {

        Long num =0L;
        Item item =new Item();
        when(itemStorable.getSize()).thenReturn(num++);


        assertThat("", itemService,is(notNullValue()));
        assertThat("should be zero ", itemService.getSize() < 1);
        num++;

        // when(itemService).
        //when(itemService).add().
        //itemService.;

        ///Assert.assertThat("",);



        itemService.add(item);


        assertThat("should be greater than one ", itemService.getSize(), is(Matchers.greaterThan(0L)));


    }

    @Test
    public void name() throws Exception {

    }
}
