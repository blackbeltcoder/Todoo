package com.sar.sandpit;

/**
 * Created by N460906 on 13/10/2016.
 */

public class ItemDto {
    private long id;
    private  String details;

    public void setDetails(String details) {
        this.details = details;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemDto(){
        this(0,"");
    }

    public ItemDto(long id) {
        this(id, "");
    }

    public ItemDto(long id, String task) {
        this.id=id;
        this.details = task;
    }

    public <T> long getId() {
        return id;
    }

    public <T> String getDetails() {
        return details;
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
