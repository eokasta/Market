package com.github.eokasta.market.interfaces;

public interface Item {

    String getName();
    int getId();
    double getValue();
    int getTotal();
    void setTotal(int value);

}
