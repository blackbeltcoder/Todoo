package com.sar.sandpit;

import java.util.HashMap;

/**
 * Created by genio on 20/10/16.
 */
public class FakeItemRepository {//implements ItemStorable {


    private static final HashMap<Long, Item> ITEMS = new HashMap<>() ;
    private static Long  SIZE =0L;

    public FakeItemRepository(){
        ITEMS.clear();
    }


    //@Override
    public void delete(Long aLong) {

    }

    Item item = new Item(0);

    //@Override
    public Long getSize() {
        return Long.valueOf(ITEMS.size());
    }

    //@Override
    public void addItem(Item item) {


    }

   // @Override
    public boolean delete(Item item) {
        ITEMS.remove(item.getId());
        return false;
    }

   // @Override
    public <T> Item getItem(long i) {
        return ITEMS.get(i);
    }


    public boolean save(Item item) {

//        if ( ITEMS.get(item.getId())==null)
            ITEMS.put(item.getId(), item);


        //SIZE++;

//        this.item = item;
        return false;
    }
}
