package com.sar.sandpit;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by N460906 on 13/10/2016.
 */
@Service
public class ItemService implements ItemServiceable {

    private  ItemStorable itemStore;

    @Override
    public boolean updateItem(Item item) {
        itemStore.saveItem(item);
        return false;
    }

    public ItemService(ItemStorable itemStorable) {

        itemStore = itemStorable;


    }
    public ItemService(){}


    @Override
    public void add(Item item) {
        itemStore.saveItem(item);


    }

    @Override
    public boolean delete(Item item) {

        itemStore.deleteItem(item);
        return false;
    }

    @Override
    public Item retrieve(long id) {
        return null;
    }

    @Override
    public Long getSize() {
        return itemStore.getSize();
    }

    @Override
    public void setStorable(ItemStorable itemStorable) {

    }
}
