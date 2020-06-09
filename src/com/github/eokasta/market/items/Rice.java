package com.github.eokasta.market.items;

import com.github.eokasta.market.interfaces.Item;

public class Rice implements Item {

    public static int totalAmount = 36;

    private final double value;
    private final int id;

    public Rice(double value, int id) {
        this.value = value;
        this.id = id;
    }

    @Override
    public double getValue() { return value; }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return this.getClass().getSimpleName(); }

    @Override
    public int getTotal() { return totalAmount; }

    @Override
    public void setTotal(int value) {
        totalAmount = value;
    }

}
