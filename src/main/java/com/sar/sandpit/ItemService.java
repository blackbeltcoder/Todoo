package com.sar.sandpit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by N460906 on 13/10/2016.
 */
@Service
public class ItemService implements ItemServiceable {

    @Autowired
    public ItemService(ItemStorable itemStorable) {

        itemStore = itemStorable;


    }
    public ItemService(){}




    //@Autowired
    private  ItemStorable itemStore;

    @Override
    public boolean updateItem(Item item) {
        itemStore.save(item);
        return false;
    }

    @Override
    public Item     getItem(long id) {
        return itemStore.findOne(id);
    }

    @Override
    public List<Item> getItems() {
        return (List<Item>) itemStore.findAll();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Item add(Item item) {
        return itemStore.save(item);
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
