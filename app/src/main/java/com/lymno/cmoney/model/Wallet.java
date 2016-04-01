package com.lymno.cmoney.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public static Wallet getByID(int id) {
        Wallet wallet = new Select()
                .from(Wallet.class)
                .where("id = ?", id)
                .executeSingle();

        wallet.setOperations(new ArrayList<>(wallet.getStoredOperations()));
        wallet.loadFriends();
        return wallet;
    }


//   багованный метод
    private ArrayList<WalletOperation> getActualOperations(){
        Date currentDate = new Date();
        ArrayList<WalletOperation> operations = new ArrayList<>();
        if (currentDate.getDate() < startDay){
            for(WalletOperation op : operations){
                if (op.getDate().getDate() >= startDay && (op.getDate().getMonth() + 1) == currentDate.getMonth()){
                    operations.add(op);
                }
                if (op.getDate().getDate() < startDay && (op.getDate().getMonth()) == currentDate.getMonth()){
                    operations.add(op);
                }
            }
            return operations;
        }
        else{
            for(WalletOperation op : operations){
                if (op.getDate().getDate() >= startDay && op.getDate().getMonth() == currentDate.getMonth()){
                    operations.add(op);
                }
            }
            return operations;
        }
    }

    public long getGoalStatus(){
        long sum = 0;
        Date date = new Date();
        ArrayList<WalletOperation> actualOp = getActualOperations();
        for(WalletOperation op : actualOp){
            sum+=op.getSum();
        }
        return sum;
    }

    public String getGoalStatusText(){
        return moneyFormat(getGoalStatus()) + "/" + moneyFormat(goal) + " \u20BD";
    }
//багованный код
//    public long getDaily(){
//        Date current = new Date();
//        long difference;
//        if(current.getDate() >= startDay){
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new Date());
//            calendar.
//
//            difference = current.getTime() - new Date(current.getYear(), cu, 27).getTime();
//        }
//
//        int days = (int) (difference / (24 * 60 * 60 * 1000));
//        return (goal - getGoalStatus()) / days;
//    }

    public static String moneyFormat(long sum){
        return sum / 100 + "," +
                (sum % 100 == 0 ? "00" : sum % 100);
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
