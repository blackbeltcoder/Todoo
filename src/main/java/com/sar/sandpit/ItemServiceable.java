package com.sar.sandpit;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by N460906 on 13/10/2016.
 */
//@Service  // todo is this needed at the interface level
public interface ItemServiceable {
    Item add(Item item);
    Item retrieve(long id);
    Long getSize();
    void setStorable(ItemStorable itemStorable);

    boolean delete(Item item);

    boolean updateItem(Item item);

    Item getItem(long l);

    List<Item> getItems();
}
