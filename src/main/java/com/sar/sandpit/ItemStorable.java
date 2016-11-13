package com.sar.sandpit;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by N460906 on 13/10/2016.
 */
public interface ItemStorable extends CrudRepository<Item,Long> {
    //Long count();


   // void addItem(Item capture);

   // boolean delete(Item item);
//
 //   boolean save(Item item);

   // <T> Item findOne(long i);
}
