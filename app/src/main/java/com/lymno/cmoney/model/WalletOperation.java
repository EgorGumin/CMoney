package com.lymno.cmoney.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "WalletOperations", id = "_id")
public class WalletOperation extends MyModel{
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
}
