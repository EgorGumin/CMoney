package com.lymno.cmoney.model;

import java.util.ArrayList;

/**
 * Created by color on 01.04.2016.
 */
public class Wallet {
    private int id;
    private String name;
    private int balance;
    private ArrayList<String> friends;
    private ArrayList<WalletOperation> operations;

    public Wallet(int id, String name, int balance, ArrayList<String> friends, ArrayList<WalletOperation> operations) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.friends = friends;
        this.operations = operations;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public ArrayList<WalletOperation> getOperations() {
        return operations;
    }
}
