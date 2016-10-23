package com.sar.sandpit;

import java.math.BigInteger;

/**
 * Created by N460906 on 13/10/2016.
 */
public class Item {
    private final String details;
    private long id;

    public Item(){
        this(0,"");
    }

    public Item(long id) {
        this(id, "");
    }

    public Item(long id, String task) {
        this.id=id;
        this.details = task;
    }

    public <T> long getId() {
        return id;
    }

    public <T> String getDetails() {
        return details;
    }
}
