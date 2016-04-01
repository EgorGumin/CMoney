package com.lymno.cmoney;

import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.WalletOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Server {
    public static ArrayList<Wallet> getWallets(){
//        Wallet: int id, String name, int balance, long goal, int startDay, ArrayList<String> friends, ArrayList<WalletOperation> operations
//        WalletOperation: String name, String login, Date date, long sum
        ArrayList<Wallet> wallets = new ArrayList<>();
        ArrayList<WalletOperation> walletOperations0 = new ArrayList<>();
        ArrayList<WalletOperation> walletOperations1 = new ArrayList<>();
        ArrayList<WalletOperation> walletOperations2 = new ArrayList<>();

        walletOperations0.add(new WalletOperation("Лента", "rhinrei", new Date(2016, 4, 1), 334400));
        walletOperations0.add(new WalletOperation("БСК", "rhinrei", new Date(2016, 3, 31), 96000));
        walletOperations0.add(new WalletOperation("Самокаты", "rhinrei", new Date(2016, 3, 27), 32300));
        walletOperations0.add(new WalletOperation("Перекресток", "coloredlime", new Date(2016, 3, 16), 120000));
        walletOperations0.add(new WalletOperation("Кафе", "coloredlime", new Date(2016, 3, 13), 86000));
        walletOperations0.add(new WalletOperation("Общежитие", "rhinrei", new Date(2016, 3, 10), 124000));
        walletOperations0.add(new WalletOperation("Экскурсия", "coloredlime", new Date(2016, 3, 7), 160000));
        walletOperations0.add(new WalletOperation("БСК", "coloredlime", new Date(2016, 3, 2), 96000));

        walletOperations1.add(new WalletOperation("Канцтовары", "bronenosez", new Date(2016, 3, 19), 257000));
        walletOperations1.add(new WalletOperation("Бумага и картриджи", "sinitsa", new Date(2016, 3, 12), 113000));
        walletOperations1.add(new WalletOperation("Кофе/чай", "coloredlime", new Date(2016, 3, 7), 57000));

        walletOperations2.add(new WalletOperation("Уборщица", "accordeon", new Date(2016, 3, 9), 2500000));
        walletOperations2.add(new WalletOperation("Интернет", "zemlecop", new Date(2016, 3, 15), 70000));
        walletOperations2.add(new WalletOperation("Домофон", "coloredlime", new Date(2016, 3, 9), 54000));

        ArrayList<String> friends0 = new ArrayList<>();
        friends0.addAll(Arrays.asList("Лисица"));

        ArrayList<String> friends1 = new ArrayList<>();
        friends0.addAll(Arrays.asList("Кот", "Синица", "Броненосец"));

        ArrayList<String> friends2 = new ArrayList<>();
        friends0.addAll(Arrays.asList("Кот", "Землекоп", "Дядя Вася", "Аккордеон", "Антон Петрович"));

        wallets.add(new Wallet(1, "Семья", (120000 + 96000 + 86000 + 160000) - (334400 + 96000 + 120000 + 124000 + 96000 + 32300 + 86000 + 160000) / 2, 900000, 2, friends0, walletOperations0));
        wallets.add(new Wallet(2, "Коллеги", 57000 - (257000 + 113000 + 57000) / 4, 2000000, 5, friends1, walletOperations1));
        wallets.add(new Wallet(3, "Соседи", 54000 - (2500000 + 70000 + 54000) / 6, 5000000, 3, friends2, walletOperations2));

        return wallets;
    }
}
