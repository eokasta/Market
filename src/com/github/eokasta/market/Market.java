package com.github.eokasta.market;

import com.github.eokasta.market.entities.Client;
import com.github.eokasta.market.utils.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Market {

    public static NumberFormat formatter = new DecimalFormat("#0.00");

    public static Manager manager = new Manager();
    public static Logger logger = new Logger();

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final Client client = new Client();
        manager.registerItems();

        manager.infos(client);

    }



}
