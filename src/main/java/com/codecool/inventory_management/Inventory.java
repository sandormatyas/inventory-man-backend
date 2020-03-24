package com.codecool.inventory_management;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.ArrayList;
import java.util.List;

@BsonDiscriminator
public class Inventory {
    @BsonId
    private int id;
    private String name;
    private List<Item> items = new ArrayList<>();


    public Inventory() {
    }

    public Inventory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addProductToInventory(Item item) {
        items.add(item);
    }

    public void removeItemFromInventory(Item item) {
        items.remove(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
