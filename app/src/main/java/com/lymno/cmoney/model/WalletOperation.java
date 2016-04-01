package com.lymno.cmoney.model;

import java.util.Date;

/**
 * Created by color on 01.04.2016.
 */
public class WalletOperation {
    private String name;
    private String login;
    private Date date;
    private long sum;

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
