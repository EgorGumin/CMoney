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
        walletOperations0.add(new WalletOperation("Лента", "coloredlime", new Date(2016, 4, 1), 334400));
        walletOperations0.add(new WalletOperation("БСК", "rhinrei", new Date(2016, 3, 30), 87500));
        ArrayList<String> friends0 = new ArrayList<>();
        friends0.addAll(Arrays.asList("Настя", "Кот"));
        wallets.add(new Wallet(1, "Семья", (334400 + 87500) / 2 - 334400, 9000, 15, friends0, walletOperations0));


        return wallets;
    }
}
