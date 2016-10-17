package com.sar.sandpit;

/**
 * Created by N460906 on 13/10/2016.
 */
public interface ItemStorable {
    Long getSize();


    void addItem(Item capture);

    boolean deleteItem(Item item);
}
