package com.sar.sandpit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.times;
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

    //////////////////////////////
    //todo delete single item
    /////////////////////////////


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

    //////////////////////////////
    //todo update single item
    /////////////////////////////


    @Test
    public void updateTask_Success() throws Exception {
        Item item = new Item();

        itemService.updateItem(item);

        verify(itemStorable).save(itemCapture.capture());
        assertThat("item should be deleted ",itemCapture.getValue(), is(equalTo(item)));
    }

    //////////////////////////////
    // todo display single item
    /////////////////////////////


    @Test
    public void getItem_success() throws Exception {

        Long id = 1L;
        itemService.getItem(id);

        verify(itemStorable).findOne(idCapture.capture());
        assertThat("id should match",idCapture.getValue(),is(equalTo(id)));
    }

    @Test
    public void getItem_success2() throws Exception {

        long id =1L;
        //Arrange
        when(itemStorable.findOne(id)).thenReturn(new Item(id));
        Item res =itemService.getItem(id);
        assertThat("item not null", res, is(notNullValue()));
        assertThat("item not equal to id ",id,is(equalTo(res.getId())));

    }
    @Test
    public void getItem_success3() throws Exception {

        long id =2L;
        //Arrange
        when(itemStorable.findOne(id)).thenReturn(new Item(id));
        Item res =itemService.getItem(id);
        assertThat("item not null", res, is(notNullValue()));
        assertThat("item not equal to id ",id,is(equalTo(res.getId())));

    }

    //////////////////////////////
    // todo display multiple items
    /////////////////////////////

    @Test
    public void getMultipleItems_ItemsExist_Success() throws Exception {

       // Item[] item = new Item[]{new Item(),new Item()};
        List<Item> items = Arrays.asList(new Item(),new Item());
        when(itemStorable.findAll()).thenReturn(items);
        itemService.getItems();
        verify(itemStorable,times(1)).findAll();




    }


    //////////////////////////////
    // todo add single item
    /////////////////////////////

    @Test
    public void addMultiple_using_explicitCurry_success(){

        //Arrange
        Function<Integer,Integer> addThree = x-> x+3;
        Function<Integer,Integer> multiplyFour= x-> x*4;
        //method one by more imperative
        Function<Integer, Integer>  addThreeThenMuliplyByFour = x-> multiplyFour.apply(addThree.apply(x));
        //
        Supplier<Function<Integer,Integer>> addThreeThenMuliplyByFourB =()-> y -> (y+3)*4;
        Supplier<Function<Integer,Integer>> addThreeThenMuliplyByFourC =()-> addThreeMultiplyByFourC();
        //method ref
        Function<Integer,Integer> addThreeThenMuliplyByFourD =this::addThreeMultiplyByFourD;

        //method two  more declarative 1
        Function<Integer,Integer> addThreeThenMuliplyByFourComposition =  multiplyFour.compose(addThree);
        //method three more delcartive 2
        Function<Integer, Integer> addThreeThenMuliplyByFour_andThen      =    addThree.andThen(multiplyFour);

        //Act
        int num = 2;
        int res =
                //addThreeThenMuliplyByFour.apply(2) ;
                //addThreeThenMuliplyByFourComposition.apply(2);
                addThreeThenMuliplyByFour_andThen.apply(num);

        //Assert
        // 2+3*4=20
        assertThat(addThreeThenMuliplyByFour.apply(num),
                   is(equalTo(20)));
        assertThat(addThreeThenMuliplyByFourB.get().apply(num),
                   is(equalTo(20)));
        assertThat(addThreeThenMuliplyByFourC.get().apply(num),
                   is(equalTo(20)));
        assertThat(addThreeThenMuliplyByFourD.apply(num),
                   is(equalTo(20)));
        assertThat(addThreeThenMuliplyByFourComposition.apply(num),
                   is(equalTo(20)));
        assertThat(res, is(equalTo(20)));


    }

    private Function<Integer, Integer> addThreeMultiplyByFourC() {
        return y -> (y+3)*4;
    }
    private Integer addThreeMultiplyByFourD(Integer y) {
        return (y+3)*4;
    }


}
