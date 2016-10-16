package com.sar.sandpit;

/**
 * Created by N460906 on 13/10/2016.
 */
public class ItemService implements ItemServiceable {

    private  ItemStorable itemStore;

    public ItemService(ItemStorable itemStorable) {

        itemStore = itemStorable;


    }
    public ItemService(){}


    @Override
    public void add(Item item) {
        itemStore.addItem(item);

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
