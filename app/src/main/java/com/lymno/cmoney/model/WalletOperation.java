package com.lymno.cmoney.model;

import android.support.annotation.NonNull;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "WalletOperations", id = "_id")
public class WalletOperation extends MyModel implements Comparable<WalletOperation>{
    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "date")
    private Date date;

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
        return date.getDate() + "." + date.getMonth() + "." + date.getYear();
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