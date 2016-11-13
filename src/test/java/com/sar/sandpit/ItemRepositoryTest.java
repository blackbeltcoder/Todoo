package com.sar.sandpit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by genio on 20/10/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DataJpaTest
public class ItemRepositoryTest {

   // private ItemStorable itemRepo2;

    @Autowired
    ItemStorable  itemRepo2;

    @Before
    public void setUp() throws Exception {
       // itemRepo2 = new FakeItemRepository();
        itemRepo2.deleteAll();
        assertThat(itemRepo2, is(notNullValue()));

    }

    @Test
    public void saveItemToRepository_emptyRepo_success() throws Exception {

      //  ItemStorable itemRepo2 = new FakeItemRepository();

        //// TODO: 24/10/16 refactor to method build basic repo list
        assertThat("should increase by one    ",itemRepo2.count(), is(equalTo(0L)));
        itemRepo2.save(new Item(1));
        assertThat("should increase by one    ",itemRepo2.count(), is(equalTo(1L)));
        itemRepo2.save(new Item(2));
        assertThat("should increase by one    ",itemRepo2.count(), is(equalTo(2L)));


    }

    @Test
    public void saveItems_emptyRepo_ItemIdsInRepoMustBeUnique() throws Exception {

        Long id = itemRepo2.save(new Item(1)).getId();
        Long id2 =itemRepo2.save(new Item(2)).getId();
        assertThat(" IDs  should be unique ", itemRepo2.findOne(id).getId(),is(not(equalTo(itemRepo2.findOne(id2).getId()))));
    }


    @Test
    public void deleteItems_whileRepoNOtEmpty_success() throws Exception {

        //// TODO: 24/10/16 refactor to method build basic repo list
        List<Item> itemsArray = Arrays.asList(new Item(21),new Item(22), new Item(23)) ;
        List<Long> ids=new ArrayList<>(3);
        assertThat( " should be zero ", itemRepo2.count(), is(equalTo(0L)));
        for (Item i :itemsArray) {
            ids.add( itemRepo2.save(i).getId());
        }



        assertThat( " should be three ", itemRepo2.count(), is(equalTo(3L)));


        itemRepo2.delete(ids.get(0));//itemsArray.get(0));
        assertThat( " should be two ", itemRepo2.count(), is(equalTo(2L)));

        itemRepo2.delete(ids.get(1));//itemsArray.get(1));
        assertThat( " should be one ", itemRepo2.count(), is(equalTo(1L)));


        itemRepo2.delete(ids.get(2));//itemsArray.get(2));
        assertThat( " should be zero ", itemRepo2.count(), is(equalTo(0L)));




    }



    @Test
    public void updateRepos_itemUpdated() throws Exception {

        Item item = new Item(1L,"task");
        Long id =itemRepo2.save(item).getId();

        Item changeItem  = new Item(itemRepo2.findOne(id).getId(),"changed task");
        itemRepo2.save(changeItem);



        assertThat(" detail should be different",item.getDetails(),is(not(equalTo(itemRepo2.findOne(id).getDetails()))));

    }

    @Test
    public void getItemFromRepo_repoNotEmpty() throws Exception {
        Item item = new Item(11L);


        //ItemStorable itemRepo2 = new FakeItemRepository();

        Long id= itemRepo2.save(item).getId();
        assertThat(" item should be in repo ", id, is(equalTo(itemRepo2.findOne(id).getId())));

    }
}
