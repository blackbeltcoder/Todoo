package com.sar.sandpit;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by genio on 20/10/16.
 */
@RunWith(SpringRunner.class)
public class ItemRepositoryTest {

    private ItemStorable itemRepo;

    @Before
    public void setUp() throws Exception {
        itemRepo = new ItemRepository();

    }

    @Test
    public void saveItemToRepository_emptyRepo_success() throws Exception {

      //  ItemStorable itemRepo = new ItemRepository();

        //// TODO: 24/10/16 refactor to method build basic repo list
        assertThat("should increase by one    ",itemRepo.getSize(), is(equalTo(0L)));
        itemRepo.saveItem(new Item(3));
        assertThat("should increase by one    ",itemRepo.getSize(), is(equalTo(1L)));
        itemRepo.saveItem(new Item(4));
        assertThat("should increase by one    ",itemRepo.getSize(), is(equalTo(2L)));


    }

    @Test
    public void saveItems_emptyRepo_ItemIdsInRepoMustBeUnique() throws Exception {

        itemRepo.saveItem(new Item(1));
        itemRepo.saveItem(new Item(2));
        assertThat(" IDs  should be unique ",itemRepo.getItem(1).getId(),is(not(equalTo(itemRepo.getItem(2).getId()))));
    }


    @Test
    public void deleteItems_whileRepoNOtEmpty_success() throws Exception {

        //// TODO: 24/10/16 refactor to method build basic repo list
        List<Item> itemsArray = Arrays.asList(new Item(1),new Item(2), new Item(3)) ;
        assertThat( " should be three ",itemRepo.getSize(), is(equalTo(0L)));
        for (Item i :itemsArray) {
            itemRepo.saveItem(i);
        }



        assertThat( " should be three ",itemRepo.getSize(), is(equalTo(3L)));


        itemRepo.deleteItem(itemsArray.get(0));
        assertThat( " should be two ",itemRepo.getSize(), is(equalTo(2L)));

        itemRepo.deleteItem(itemsArray.get(1));
        assertThat( " should be one ",itemRepo.getSize(), is(equalTo(1L)));


        itemRepo.deleteItem(itemsArray.get(2));
        assertThat( " should be zero ",itemRepo.getSize(), is(equalTo(0L)));




    }



    @Test
    public void updateRepos_itemUpdated() throws Exception {

        Item item = new Item(1L,"task");
        itemRepo.saveItem(item);

        Item changeItem  = new Item(itemRepo.getItem(1L).getId(),"changed task");
        itemRepo.saveItem(changeItem);



        assertThat(" detail should be different",item.getDetails(),is(not(equalTo(itemRepo.getItem(1L).getDetails()))));

    }

    @Test
    public void getItemFromRepo_repoNotEmpty() throws Exception {
        Item item = new Item(1L);


        //ItemStorable itemRepo = new ItemRepository();

        itemRepo.saveItem(item);
        assertThat(" item should be in repo ", item.getId(), is(equalTo(itemRepo.getItem(1).getId())));

    }
}
