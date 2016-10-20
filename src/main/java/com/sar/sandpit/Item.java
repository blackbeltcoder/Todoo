package com.sar.sandpit;

import java.math.BigInteger;

/**
 * Created by N460906 on 13/10/2016.
 */
public class Item {
    private long id;

    public Item(){
        this.id = 0;
    }

    public Item(long id) {
        this.id=id;
    }

    public <T> long getId() {
        return id;
    }
}
