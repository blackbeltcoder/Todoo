package com.sar.sandpit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Created by N460906 on 13/10/2016.
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;
    private final String details;

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



    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        if(this==obj)
            return true;

        if(  !(obj instanceof Item))
            return false;

        Item item =(Item)obj;
        return id == item.id &&
                Objects.equals(details, item.getDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash( details);
    }



    public void testMethod(String test){
        System.out.println(test.toUpperCase()+"\n");
    }

    @Override
    public String toString() {
        return "Item{" +
                "details='" + details + '\'' +
                ", id=" + id +
                '}';
    }
}
