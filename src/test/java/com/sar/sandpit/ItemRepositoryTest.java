package com.sar.sandpit;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertThat("should increase by one    ",itemRepo.getSize(), is(CoreMatchers.equalTo(0L)));
        itemRepo.saveItem(new Item(3));
        assertThat("should increase by one    ",itemRepo.getSize(), is(CoreMatchers.equalTo(1L)));
        itemRepo.saveItem(new Item(4));
        assertThat("should increase by one    ",itemRepo.getSize(), is(CoreMatchers.equalTo(2L)));

    }

    @Test
    public void saveItems_emptyRepo_ItemIdsInRepoMustBeUnique() throws Exception {

        itemRepo.saveItem(new Item(1));
        itemRepo.saveItem(new Item(2));
        assertThat(" IDs  should be unique ",itemRepo.getItem(1).getId(),is(CoreMatchers.not(CoreMatchers.equalTo(itemRepo.getItem(2).getId()))));
    }

    //TODO UPDATE

    @Test
    public void getItemFromRepo_repoNotEmpty() throws Exception {
        Item item = new Item(1L);


        //ItemStorable itemRepo = new ItemRepository();

        itemRepo.saveItem(item);
        assertThat(" item should be in repo ", item.getId(), is(CoreMatchers.equalTo(itemRepo.getItem(1).getId())));

    }
}
