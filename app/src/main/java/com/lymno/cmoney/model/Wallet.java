package com.lymno.cmoney.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

@Table(name = "Wallets", id = "_id")
public class Wallet extends MyModel{
    @Expose
    @SerializedName("WalletId")
    @Column(name = "id")
    private int walletID;

    @Expose
    @SerializedName("Name")
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("Balance")
    @Column(name = "balance")
    private int balance;

    @Expose
    @SerializedName("TargetSum")
    @Column(name = "goal")
    private long goal;

    @Expose
    @SerializedName("StartDay")
    @Column(name = "start_day")
    private int startDay;

    @Expose
    @SerializedName("Logins")
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

    @Expose
    @SerializedName("Payments")
    @Column(name = "operations")
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


    public String getGoalStatusText(){
        return moneyFormat(getGoalStatus()) + "/" + moneyFormat(goal) + " \u20BD";
    }

    private int getDayYearIndex(Date date)
    {
        int sum = 0;
        int month = date.getMonth() + 1;
        for (int i =0; i < month; i++)
        {
            sum += (new GregorianCalendar(date.getYear(), i, 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        sum -= (new GregorianCalendar(date.getYear(), month + 1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH) - date.getDate();
        return sum;
    }

    private Date getStartDayYearIndex(int start, Date today)
    {
        Date startDate = null;
        if (start < today.getDate())
        {
            startDate = new Date(today.getYear(), today.getMonth(), start);
        }
        else if (start == today.getDate())
        {
            startDate = today;
        }
        else
        {
            int month = today.getMonth() - 1;
            int year = today.getYear();
            if (month == -1)
            {
                year--;
                month = 11;
            }
            startDate = new Date(year, month, start);
        }
        return startDate;
    }

    public long getGoalStatus(){
        int todayDayNumber = getDayYearIndex(new Date());
        int startDayNumber = getDayYearIndex(getStartDayYearIndex(startDay, new Date()));
        Calendar c = Calendar.getInstance();
        c.setTime(getStartDayYearIndex(startDay, new Date()));
        c.roll(Calendar.MONTH, 1);
        int nextDay = getDayYearIndex(c.getTime());

        int max = Math.max(todayDayNumber, startDayNumber);
        int min = Math.min(startDayNumber, todayDayNumber);
        ArrayList<WalletOperation> operations = this.operations;

        int differenceDays = nextDay - todayDayNumber;
        long sum = 0;

        for (WalletOperation op : operations)
        {
            int opDayNumber = getDayYearIndex(op.getDate());
            if (opDayNumber >= min && opDayNumber <= max)
            {
                sum += op.getSum();
            }
        }
        return sum;
    }

    public long getDaily() {
        int todayDayNumber = getDayYearIndex(new Date());
        int startDayNumber = getDayYearIndex(getStartDayYearIndex(startDay, new Date()));
        Calendar c = Calendar.getInstance();
        c.setTime(getStartDayYearIndex(startDay, new Date()));
        c.roll(Calendar.MONTH, 1);
        int nextDay = getDayYearIndex(c.getTime());

        int max = Math.max(todayDayNumber, startDayNumber);
        int min = Math.min(startDayNumber, todayDayNumber);
        ArrayList<WalletOperation> operations = this.operations;

        int differenceDays = nextDay - todayDayNumber;
        long sum = 0;

        for (WalletOperation op : operations)
        {
            int opDayNumber = getDayYearIndex(op.getDate());
            if (opDayNumber >= min && opDayNumber <= max)
            {
                sum += op.getSum();
            }
        }
        return (goal-sum) / differenceDays;
    }

    public long recalculateBalance(String myLogin)
    {
        ArrayList<WalletOperation> operations = getOperations();
        long sum = 0;
        long mySum = 0;
        for (WalletOperation op : operations)
        {
            sum += op.getSum();

            if (myLogin.equals(op.getLogin()))
            {
                mySum += op.getSum();
            }
        }
        int amountFriends = friends.size() + 1;
        return mySum - (sum / amountFriends);
    }


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

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
