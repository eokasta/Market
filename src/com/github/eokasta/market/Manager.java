package com.github.eokasta.market;

import com.github.eokasta.market.entities.Client;
import com.github.eokasta.market.interfaces.Item;
import com.github.eokasta.market.items.*;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {

    private final Map<Integer, Item> marketItems = new HashMap<>();

    public void registerItems() {
        registerItem(new Beef(12.50, 1));
        registerItem(new Bread(0.50, 2));
        registerItem(new Rice(7.50, 3));
        registerItem(new Bean(5.00, 4));
        registerItem(new Soda(2.50, 5));
    }

    public Item getItem(int id) {
        return marketItems.get(id);
    }

    public Map<Integer, Item> getItems() {
        return marketItems;
    }

    public List<Item> sortedItemsById() {
        final List<Integer> integerList = new ArrayList<>(getItems().keySet());
        Collections.sort(integerList);
        return integerList.stream().map(this::getItem).collect(Collectors.toList());
    }

    private void registerItem(Item item) {
        marketItems.put(item.getId(), item);
    }


    public void infos(Client client) {
        Market.logger.log("Seu saldo é de: R$ " + Market.formatter.format(client.getBalance()));
        Market.logger.log("");

        Market.logger.log("Itens no mercado:");
        for (final Item item : sortedItemsById()) {
            Market.logger.log(item.getId() + " - " + item.getName() + " R$ "
                    + Market.formatter.format(item.getValue()) + " - Estoque: " + item.getTotal());
        }

        Market.logger.log("");
        Market.logger.log("Caso queira concluir sua compra, digite: 'concluir'");
        Market.logger.log("Digite o item e a quantidade que deseja comprar: ");

        scan(client);

    }

    private void scan(Client client) {
        final String string = Market.scanner.nextLine();

        if (string.trim().equalsIgnoreCase("concluir")) {
            Market.logger.log("Você concluiu sua compra!");
            Market.logger.log("");
            if (client.totalCartPrice() == 0.0) {
                Market.logger.log("Você não comprou nada.");
                return;
            }
            Market.logger.log("Total de sua compra: R$ " + Market.formatter.format(client.totalCartPrice()));
            for (final Map.Entry<Item, Integer> entry : client.getCart().entrySet()) {
                Market.logger.log(entry.getValue() + "x " + entry.getKey().getName() + " (R$ %s)",
                        Market.formatter.format(entry.getKey().getValue() * entry.getValue()));
            }
            Market.logger.log("");
            Market.logger.log("Obrigado pela preferência!");
            return;
        }

        if (!string.contains(" ")) {
            Market.logger.log("Erro ocorreu no sistema, tente novamente.");
            infos(client);
            return;
        }

        final int id = getInt(string.split(" ")[0]);
        final int amount = getInt(string.split(" ")[1]);

        if (id <= 0 || !marketItems.containsKey(id)) {
            Market.logger.log("O produto inserido está fora de cogitação.");
            infos(client);
            return;
        }

        final Item item = getItem(id);

        if (amount <= 0 || item.getTotal() < amount) {
            Market.logger.log("O produto que você deseja comprar possui somente %s unidades.", "" + item.getTotal());
            infos(client);
            return;
        }

        final double amountTotal = client.getAmount(item) + item.getValue() * amount;

        if (amountTotal > client.getBalance()) {
            Market.logger.log("Você não possui %s para efetuar a compra.",
                    "R$ " + Market.formatter.format(item.getValue() * amount));
            infos(client);
            return;
        }

        client.addItem(item, amount);
        Market.logger.log("Você adicionou x%s %s(s) ao seu carrinho.", "" + amount, item.getName());
        infos(client);
    }

    private int getInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception exception) {
            return 0;
        }
    }
}
