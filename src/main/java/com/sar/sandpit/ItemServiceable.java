package com.sar.sandpit;

/**
 * Created by N460906 on 13/10/2016.
 */
public interface ItemServiceable {
    void add(Item item);
    Item retrieve(long id);
    Long getSize();
    void setStorable(ItemStorable itemStorable);
}
