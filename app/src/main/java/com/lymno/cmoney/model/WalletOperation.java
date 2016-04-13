package com.lymno.cmoney.model;

import android.support.annotation.NonNull;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

@Table(name = "WalletOperations", id = "_id")
public class WalletOperation extends MyModel implements Comparable<WalletOperation>{
    @Expose
    @SerializedName("Text")
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("Login")
    @Column(name = "login")
    private String login;

    @Expose
    @SerializedName("Date")
    @Column(name = "date")
    private Date date;

    @Expose
    @SerializedName("Value")
    @Column(name = "sum")
    private long sum;

    @Column(name = "wallet", onDelete = Column.ForeignKeyAction.CASCADE)
    public Wallet wallet;

    public WalletOperation(String name, String login, Date date, long sum) {
        this.name = name;
        this.login = login;
        this.date = date;
        this.sum = sum;
    }

    public WalletOperation(){

    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public Date getDate() {
        return date;
    }

    public long getSum() {
        return sum;
    }

    public String getSumText(){
        return sum / 100 + "," +
                (sum % 100 == 0 ? "00" : sum % 100) + " \u20BD";
    }

    public String getDateText(){
//        return (date.getDate()) + "." + (date.getMonth() + 1) + "." + date.getYear();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH) +"."+ (c.get(Calendar.MONTH) +1 )+"." + c.get(Calendar.YEAR) + "";
    }

    public String getTimeText(){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY) +":"+ c.get(Calendar.MINUTE) + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WalletOperation that = (WalletOperation) o;

        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public int compareTo(@NonNull WalletOperation another) {
        return this.getDate().compareTo(another.getDate());
    }
}