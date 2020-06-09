package com.github.eokasta.market.items;

import com.github.eokasta.market.interfaces.Item;

public class Bread implements Item {

    public static int totalAmount = 13;

    private final double value;
    private final int id;

    public Bread(double value, int id) {
        this.value = value;
        this.id = id;
    }

    @Override
    public double getValue() { return value; }

    @Override
    public int getTotal() { return totalAmount; }

    @Override
    public void setTotal(int value) {
        totalAmount = value;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return this.getClass().getSimpleName(); }
}
