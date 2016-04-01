package com.lymno.cmoney.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Wallets", id = "_id")
public class Wallet extends MyModel{

    @Column(name = "id")
    private int walletID;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private int balance;

    @Column(name = "goal")
    private long goal;

    @Column(name = "start_day")
    private int startDay;

    private ArrayList<String> friends;

    @Column(name = "friends")
    private String storedFriends;

    public void loadFriends() {
        Gson gson = new Gson();
        friends = gson.fromJson(storedFriends, new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    private void saveFriends() {
        Gson gson = new Gson();
        this.storedFriends = gson.toJson(friends);
    }

    @Column(name = "friends")
    private ArrayList<WalletOperation> operations;


    public Wallet(){
        //orm requires
    }

    public Wallet(int id, String name, int balance, long goal, int startDay, ArrayList<String> friends, ArrayList<WalletOperation> operations) {
        this.walletID = id;
        this.name = name;
        this.balance = balance;
        this.goal = goal;
        this.startDay = startDay;
        this.friends = friends;
        this.operations = operations;
    }

    //Сохраняет кошелек и его операции
    public void saveFull() {
        saveFriends();
        save();
        for (WalletOperation w : operations) {
            w.wallet = this;
            w.save();
        }
    }

    public static void recreate(ArrayList<Wallet> wallets) {
        truncate(Wallet.class);
        truncate(WalletOperation.class); //возможно, это лишнее
        save(wallets);
    }

    //Сохраняет список кошельков с их операциями
    public static void save(ArrayList<Wallet> wallets) {
        for (Wallet wallet : wallets) {
            wallet.saveFull();
        }
    }

    public static ArrayList<Wallet> getAll() {
        List<Wallet> wallets = new Select().from(Wallet.class).execute();
        ArrayList<Wallet> walletsWithOperations = new ArrayList<>();
        for (Wallet wallet : wallets) {
            wallet.setOperations(new ArrayList<>(wallet.getStoredOperations()));
            wallet.loadFriends();
            walletsWithOperations.add(wallet);
        }
        return walletsWithOperations;
    }

    public List<WalletOperation> getStoredOperations() {
        List<WalletOperation> walletOperations = getMany(WalletOperation.class, "Wallet");
        return walletOperations;
    }

    public int getWalletID() {
        return walletID;
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

    public long getGoal() {
        return goal;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setOperations(ArrayList<WalletOperation> operations) {
        this.operations = operations;
    }
}
