package com.github.eokasta.market.entities;

import com.github.eokasta.market.interfaces.Item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Client {

    private final Map<Item, Integer> cart;
    private double balance = 50.00;

    public Client() {
        cart = new HashMap<>();
    }

    public Map<Item, Integer> getCart() { return cart; }

    public Set<Item> getItems() {
        return cart.keySet();
    }

    public int getAmount(Item item) {
        return cart.getOrDefault(item , 0);
    }

    public double getPrice(Item item) {
        return item.getValue() * getAmount(item);
    }

    public double totalCartPrice() {
        return getItems().stream().mapToDouble(item -> item.getValue() * getAmount(item)).sum();
    }

    public double getBalance() { return balance; }

    public void addBalance(double value) {
        balance += value;
    }

    public void removeBalance(double value) {
        balance = (balance - value < 0 ? 0 : balance - value);
    }

    private void setBalance(double balance) { this.balance = balance; }

    public void addItem(Item item, int amount) {
        getCart().put(item, amount);
        item.setTotal(item.getTotal() - amount);
        removeBalance(item.getValue() * amount);
    }
}
