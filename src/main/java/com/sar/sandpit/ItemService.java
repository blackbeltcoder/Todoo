package com.sar.sandpit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by N460906 on 13/10/2016.
 */
@Service
public class ItemService implements ItemServiceable {

    @Autowired
    private  ItemStorable itemStore;

    @Override
    public boolean updateItem(Item item) {
        itemStore.save(item);
        return false;
    }

    @Override
    public Item getItem(long id) {

        itemStore.findOne(id);
        return null;
        //return new Item();
    }

    public ItemService(ItemStorable itemStorable) {

        itemStore = itemStorable;


    }
    public ItemService(){}


    @Override
    public void add(Item item) {
        itemStore.save(item);


    }

    @Override
    public boolean delete(Item item) {
        itemStore.delete(item);
        return itemStore.exists(item.getId());
    }

    @Override
    public Item retrieve(long id) {
        return null;
    }

    @Override
    public Long getSize() {
        return itemStore.count();
    }

    @Override
    public void setStorable(ItemStorable itemStorable) {

    }
}
